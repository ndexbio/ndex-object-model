# CX1 → CX2 Node Label Position Conversion

This document describes how the `org.ndexbio.cx2.converter` package converts a
CX1 `NODE_LABEL_POSITION` visual property value (a serialized string from
Cytoscape Desktop's vizmapper) into a CX2 `NODE_LABEL_POSITION` object (a
structured value modeled after Cytoscape.js).

## Why the conversion is approximate

The two label-position data models come from different rendering engines and
they are not strictly compatible:

- **CX1** uses the Cytoscape Desktop vizmapper model. A single label position
  is encoded as a comma-separated string with five tokens that combine a
  *node anchor*, a *label alignment*, a text *justification*, and a pixel
  *(MarginX, MarginY)* offset. The anchor and alignment use compass-letter
  codes (`N`, `S`, `E`, `W`, `NE`, `NW`, `SE`, `SW`, `C`).
- **CX2** is modeled after the Cytoscape.js label-position model, which
  natively expresses position via `text-halign` / `text-valign` plus margins.
  CX2 keeps both an *anchor* and an *alignment* (each split into a horizontal
  and a vertical axis) so most of the CX1 information can be carried over.

For each individual token the syntactic conversion is lossless on the set of
canonical CX1 values, but the *rendered* result still depends on the renderer
honoring both ANCHOR and ALIGN — Cytoscape.js itself only directly understands
`text-halign` / `text-valign`. That is the sense in which the conversion is
"approximate" at the visual level.

## Entry point

`CXToCX2VisualPropertyConverter` registers the conversion lambda for
`NODE_LABEL_POSITION`:
[CXToCX2VisualPropertyConverter.java:175](../src/main/java/org/ndexbio/cx2/converter/CXToCX2VisualPropertyConverter.java)

```java
addEntry("NODE_LABEL_POSITION",
    (positionStr) -> { return LabelPosition.createFromCX1Value(positionStr); });
```

The lambda delegates to `LabelPosition.createFromCX1Value`, which in turn
calls `ObjectPosition.populateFromCX1Values` for the shared fields and then
sets the label-only `JUSTIFICATION` field.

## CX1 input format

The CX1 value is a single string with five comma-separated tokens:

```
<anchor>,<alignment>,<justification>,<marginX>,<marginY>
```

Example: `"SE,NW,l,0.00,27.00"`

| Token index | Meaning | Allowed CX1 values |
|------------:|---------|--------------------|
| 0 | Anchor on the **node** | `C`, `N`, `S`, `E`, `W`, `NE`, `NW`, `SE`, `SW` |
| 1 | Alignment on the **label** | same set as anchor |
| 2 | Text justification within the label | `l`, `c`, `r` |
| 3 | MarginX (horizontal pixel offset) | floating-point string |
| 4 | MarginY (vertical pixel offset) | floating-point string |

## CX2 output object

`LabelPosition` (extends `ObjectPosition`) serializes to a JSON object with
the following keys:

| Key | Type | Source token |
|-----|------|--------------|
| `HORIZONTAL_ANCHOR` | `left` \| `center` \| `right` | token 0 (anchor) |
| `VERTICAL_ANCHOR`   | `top`  \| `center` \| `bottom` | token 0 (anchor) |
| `HORIZONTAL_ALIGN`  | `left` \| `center` \| `right` | token 1 (alignment) |
| `VERTICAL_ALIGN`    | `top`  \| `center` \| `bottom` | token 1 (alignment) |
| `JUSTIFICATION`     | `left` \| `center` \| `right` | token 2 |
| `MARGIN_X`          | float                          | token 3 |
| `MARGIN_Y`          | float                          | token 4 |

## Conversion algorithm

`LabelPosition.createFromCX1Value(cx1Value)` does the following:

1. Constructs a fresh `LabelPosition`. All anchor/alignment fields default
   to `center`; both margins default to `0`.
2. Splits the CX1 string on commas.
3. Calls `ObjectPosition.populateFromCX1Values(result, values)` which:
   - Calls `setAnchorFromCX1(values[0])` — iterates each character of the
     anchor token and applies `setAnchorFromSingleLeter` per character. Each
     letter touches at most one of `verticalAnchor`/`horizontalAnchor`; any
     axis not mentioned keeps its default of `center`.
   - Calls `setAlignmentFromCX1(values[1])` — same logic for
     `verticalAlign`/`horizontalAlign`.
   - Calls `setMarginsFromCX1(values[3], values[4])` — `Float.parseFloat` on
     each token.
4. Calls `setJustification(HorizontalAlignment.fromCX1(values[2]))`.

Note that `values[2]` is read in `LabelPosition` (not in `ObjectPosition`),
which is why the base class skips index 2.

## Token-by-token mapping tables

### Anchor (token 0) — also applies to Alignment (token 1)

The anchor and alignment tokens are processed by the same letter-by-letter
algorithm, so the table below applies to both. For alignment, replace
`*_ANCHOR` with `*_ALIGN` in the output columns.

| CX1 value | `VERTICAL_ANCHOR` | `HORIZONTAL_ANCHOR` | Precise? |
|-----------|-------------------|---------------------|----------|
| `C`       | `center`          | `center`            | Yes |
| `N`       | `top`             | `center`            | Yes |
| `S`       | `bottom`          | `center`            | Yes |
| `E`       | `center`          | `right`             | Yes |
| `W`       | `center`          | `left`              | Yes |
| `NE`      | `top`             | `right`             | Yes |
| `NW`      | `top`             | `left`              | Yes |
| `SE`      | `bottom`          | `right`             | Yes |
| `SW`      | `bottom`          | `left`              | Yes |

Edge cases / non-canonical inputs:

| CX1 value | Result | Precise? |
|-----------|--------|----------|
| `EN`, `WN`, `ES`, `WS` (reversed two-letter codes) | Same as `NE` / `NW` / `SE` / `SW` because each letter is processed independently and they touch different axes | Yes (round-trip via `toCX1String` will normalize order) |
| Two letters on the same axis, e.g. `NS`, `EW` | The second letter overwrites the first (e.g. `NS` → `vertical = bottom`); these are not legal CX1 values | N/A |
| Any letter other than `N`,`S`,`E`,`W`,`C` | Silently ignored (`default` branch of `setAnchorFromSingleLeter`) | N/A |
| Empty string | `IndexOutOfBoundsException` from `charAt(0)` | N/A |

### Justification (token 2)

Handled by `HorizontalAlignment.fromCX1`:

| CX1 value | `JUSTIFICATION` | Precise? |
|-----------|-----------------|----------|
| `l`       | `left`          | Yes |
| `c`       | `center`        | Yes |
| `r`       | `right`         | Yes |
| anything else | `IllegalArgumentException` | N/A |

### Margins (tokens 3 and 4)

Each token is parsed with `Float.parseFloat` and stored as a `float` in
`MARGIN_X` / `MARGIN_Y`.

| CX1 token | CX2 field | Precise? |
|-----------|-----------|----------|
| `marginX` (float-string) | `MARGIN_X` (float) | Yes (within IEEE-754 single-precision) |
| `marginY` (float-string) | `MARGIN_Y` (float) | Yes (within IEEE-754 single-precision) |

CX1 typically writes margins as fixed-point strings like `"27.00"`. Parsing
those into `float` is exact; very large values that exceed `float` precision
would round, but this is not expected in practice.

## Worked example

Input: `"SE,NW,l,0.00,27.00"`

1. `values = ["SE", "NW", "l", "0.00", "27.00"]`
2. `setAnchorFromCX1("SE")` → `verticalAnchor=bottom`, `horizontalAnchor=right`
3. `setAlignmentFromCX1("NW")` → `verticalAlign=top`, `horizontalAlign=left`
4. `setMarginsFromCX1("0.00", "27.00")` → `marginX=0.0f`, `marginY=27.0f`
5. `setJustification(fromCX1("l"))` → `justification=left`

Resulting CX2 object (JSON):

```json
{
  "HORIZONTAL_ANCHOR": "right",
  "VERTICAL_ANCHOR":   "bottom",
  "HORIZONTAL_ALIGN":  "left",
  "VERTICAL_ALIGN":    "top",
  "JUSTIFICATION":     "left",
  "MARGIN_X": 0.0,
  "MARGIN_Y": 27.0
}
```

Semantically: anchor the label so the **bottom-right** of the node is the
reference point, then align the label's **top-left** to that point, justify
text within the label to the **left**, and offset the label by `(0, 27)`
pixels. Cytoscape Desktop renders this directly; a Cytoscape.js renderer
would honor the `*_ALIGN` values (its native `text-halign` / `text-valign`)
and the margins, but the additional `*_ANCHOR` distinction is informational
unless the renderer explicitly uses it.

## Full anchor × alignment fact table

This table enumerates every legal `anchor / alignment` combination of the 9
CX1 compass codes (81 rows total). The "Same rendering?" column reflects the
following assumption about the consuming renderer: Cytoscape.js natively
models label position with `text-halign` / `text-valign` only and has no
separate label-alignment concept. A Cytoscape.js renderer therefore behaves
as if the label's alignment is the geometric *complement* of the anchor
(e.g. if the anchor is the node's right edge, the label's left edge is what
touches it). When the CX1 alignment is not that complement, the CX1 string
is encoding information Cytoscape.js cannot natively reproduce, so the
on-screen rendering will diverge from Cytoscape Desktop.

Legend:
- For horizontal: `l` = left, `c` = center, `r` = right
- For vertical:   `t` = top,  `c` = center, `b` = bottom
- `anchor=(H,V)` is shorthand for `HORIZONTAL_ANCHOR=H, VERTICAL_ANCHOR=V`
- `align=(H,V)`  is shorthand for `HORIZONTAL_ALIGN=H,  VERTICAL_ALIGN=V`

| CX1 anchor / align | Anchor position on node | CX2 value | Same rendering? |
|---|---|---|---|
| C  / C  | center        | anchor=(c,c), align=(c,c) | Yes |
| C  / N  | center        | anchor=(c,c), align=(c,t) | No  |
| C  / NE | center        | anchor=(c,c), align=(r,t) | No  |
| C  / E  | center        | anchor=(c,c), align=(r,c) | No  |
| C  / SE | center        | anchor=(c,c), align=(r,b) | No  |
| C  / S  | center        | anchor=(c,c), align=(c,b) | No  |
| C  / SW | center        | anchor=(c,c), align=(l,b) | No  |
| C  / W  | center        | anchor=(c,c), align=(l,c) | No  |
| C  / NW | center        | anchor=(c,c), align=(l,t) | No  |
| N  / C  | top-center    | anchor=(c,t), align=(c,c) | No  |
| N  / N  | top-center    | anchor=(c,t), align=(c,t) | No  |
| N  / NE | top-center    | anchor=(c,t), align=(r,t) | No  |
| N  / E  | top-center    | anchor=(c,t), align=(r,c) | No  |
| N  / SE | top-center    | anchor=(c,t), align=(r,b) | No  |
| N  / S  | top-center    | anchor=(c,t), align=(c,b) | Yes |
| N  / SW | top-center    | anchor=(c,t), align=(l,b) | No  |
| N  / W  | top-center    | anchor=(c,t), align=(l,c) | No  |
| N  / NW | top-center    | anchor=(c,t), align=(l,t) | No  |
| NE / C  | top-right     | anchor=(r,t), align=(c,c) | No  |
| NE / N  | top-right     | anchor=(r,t), align=(c,t) | No  |
| NE / NE | top-right     | anchor=(r,t), align=(r,t) | No  |
| NE / E  | top-right     | anchor=(r,t), align=(r,c) | No  |
| NE / SE | top-right     | anchor=(r,t), align=(r,b) | No  |
| NE / S  | top-right     | anchor=(r,t), align=(c,b) | No  |
| NE / SW | top-right     | anchor=(r,t), align=(l,b) | Yes |
| NE / W  | top-right     | anchor=(r,t), align=(l,c) | No  |
| NE / NW | top-right     | anchor=(r,t), align=(l,t) | No  |
| E  / C  | right-center  | anchor=(r,c), align=(c,c) | No  |
| E  / N  | right-center  | anchor=(r,c), align=(c,t) | No  |
| E  / NE | right-center  | anchor=(r,c), align=(r,t) | No  |
| E  / E  | right-center  | anchor=(r,c), align=(r,c) | No  |
| E  / SE | right-center  | anchor=(r,c), align=(r,b) | No  |
| E  / S  | right-center  | anchor=(r,c), align=(c,b) | No  |
| E  / SW | right-center  | anchor=(r,c), align=(l,b) | No  |
| E  / W  | right-center  | anchor=(r,c), align=(l,c) | Yes |
| E  / NW | right-center  | anchor=(r,c), align=(l,t) | No  |
| SE / C  | bottom-right  | anchor=(r,b), align=(c,c) | No  |
| SE / N  | bottom-right  | anchor=(r,b), align=(c,t) | No  |
| SE / NE | bottom-right  | anchor=(r,b), align=(r,t) | No  |
| SE / E  | bottom-right  | anchor=(r,b), align=(r,c) | No  |
| SE / SE | bottom-right  | anchor=(r,b), align=(r,b) | No  |
| SE / S  | bottom-right  | anchor=(r,b), align=(c,b) | No  |
| SE / SW | bottom-right  | anchor=(r,b), align=(l,b) | No  |
| SE / W  | bottom-right  | anchor=(r,b), align=(l,c) | No  |
| SE / NW | bottom-right  | anchor=(r,b), align=(l,t) | Yes |
| S  / C  | bottom-center | anchor=(c,b), align=(c,c) | No  |
| S  / N  | bottom-center | anchor=(c,b), align=(c,t) | Yes |
| S  / NE | bottom-center | anchor=(c,b), align=(r,t) | No  |
| S  / E  | bottom-center | anchor=(c,b), align=(r,c) | No  |
| S  / SE | bottom-center | anchor=(c,b), align=(r,b) | No  |
| S  / S  | bottom-center | anchor=(c,b), align=(c,b) | No  |
| S  / SW | bottom-center | anchor=(c,b), align=(l,b) | No  |
| S  / W  | bottom-center | anchor=(c,b), align=(l,c) | No  |
| S  / NW | bottom-center | anchor=(c,b), align=(l,t) | No  |
| SW / C  | bottom-left   | anchor=(l,b), align=(c,c) | No  |
| SW / N  | bottom-left   | anchor=(l,b), align=(c,t) | No  |
| SW / NE | bottom-left   | anchor=(l,b), align=(r,t) | Yes |
| SW / E  | bottom-left   | anchor=(l,b), align=(r,c) | No  |
| SW / SE | bottom-left   | anchor=(l,b), align=(r,b) | No  |
| SW / S  | bottom-left   | anchor=(l,b), align=(c,b) | No  |
| SW / SW | bottom-left   | anchor=(l,b), align=(l,b) | No  |
| SW / W  | bottom-left   | anchor=(l,b), align=(l,c) | No  |
| SW / NW | bottom-left   | anchor=(l,b), align=(l,t) | No  |
| W  / C  | left-center   | anchor=(l,c), align=(c,c) | No  |
| W  / N  | left-center   | anchor=(l,c), align=(c,t) | No  |
| W  / NE | left-center   | anchor=(l,c), align=(r,t) | No  |
| W  / E  | left-center   | anchor=(l,c), align=(r,c) | Yes |
| W  / SE | left-center   | anchor=(l,c), align=(r,b) | No  |
| W  / S  | left-center   | anchor=(l,c), align=(c,b) | No  |
| W  / SW | left-center   | anchor=(l,c), align=(l,b) | No  |
| W  / W  | left-center   | anchor=(l,c), align=(l,c) | No  |
| W  / NW | left-center   | anchor=(l,c), align=(l,t) | No  |
| NW / C  | top-left      | anchor=(l,t), align=(c,c) | No  |
| NW / N  | top-left      | anchor=(l,t), align=(c,t) | No  |
| NW / NE | top-left      | anchor=(l,t), align=(r,t) | No  |
| NW / E  | top-left      | anchor=(l,t), align=(r,c) | No  |
| NW / SE | top-left      | anchor=(l,t), align=(r,b) | Yes |
| NW / S  | top-left      | anchor=(l,t), align=(c,b) | No  |
| NW / SW | top-left      | anchor=(l,t), align=(l,b) | No  |
| NW / W  | top-left      | anchor=(l,t), align=(l,c) | No  |
| NW / NW | top-left      | anchor=(l,t), align=(l,t) | No  |

Of the 81 combinations, only **9** render the same in Cytoscape Desktop and
Cytoscape.js — the rows where the CX1 alignment is the geometric complement
of the CX1 anchor: `C/C`, `N/S`, `S/N`, `E/W`, `W/E`, `NE/SW`, `SW/NE`,
`NW/SE`, `SE/NW`. These are exactly the nine combinations expressible as a
`text-halign` × `text-valign` pair in Cytoscape.js.

For the other 72 combinations, the CX1 string encodes an anchor + alignment
relationship that Cytoscape.js's native label model cannot directly express.
The converter still preserves both fields in the CX2 object — so a richer
renderer (or a CX2 → CX1 round-trip via `LabelPosition.toCX1String`) can
recover the original information — but a stock Cytoscape.js render will fall
back to its implicit complementary alignment, and the label will appear in a
different place than it does in Cytoscape Desktop.

## Rendering review: where a different CX2 ANCHOR would render closer to Desktop

The fact table above shows the CX2 value the **current converter actually
produces**. That value is correct in the sense that it preserves all of the
CX1 information losslessly — both the ANCHOR pair and the ALIGN pair carry
the original anchor/alignment letters.

However, **a stock Cytoscape.js renderer only reads the ANCHOR fields**
(mapping them onto its native `text-halign` / `text-valign`) and ignores the
ALIGN fields. Under that constraint, the current converter's choice of
ANCHOR is optimal for only **25 of the 81** combinations. For the other
**56**, a different ANCHOR pair would put the label visibly closer to where
Cytoscape Desktop draws it.

### The rule

Treat each CX1 compass code as a pair of signed components:
`{W: −1, C: 0, E: +1}` on the horizontal axis and
`{N: −1, C: 0, S: +1}` on the vertical axis. The Desktop label center is
offset from the node center by `dx ∝ anchor.h − alignment.h` and
`dy ∝ anchor.v − alignment.v`.

Cytoscape.js can place the label center at only nine discrete points
(combinations of `text-halign` × `text-valign`), so the closest one is
determined by the **signs** of those differences:

```
better.HORIZONTAL_ANCHOR = sign(anchor.h − alignment.h)   // → left | center | right
better.VERTICAL_ANCHOR   = sign(anchor.v − alignment.v)   // → top  | center | bottom
```

The ALIGN fields can be left as the converter produces them today (a
Cytoscape.js renderer ignores them) or set to `center / center` to match
Cytoscape.js's native one-pair model.

#### Caveat on label / node sizes

The sign-based rule above implicitly assumes label half-width `w` is
comparable to node half-width `W` (and likewise for the heights). When
`w >> W` (long text on small icons) or `w << W` (short labels in big
nodes), boundary cases — those where exactly one of `{anchor, alignment}`
on a given axis is the center letter — can flip between the rule's choice
and an adjacent CSj position. For typical biological-network labels the
rule below is a good approximation; if pixel-perfect rendering matters,
break ties with the actual rendered label and node sizes.

### Two notable patterns inside the 56 wrong cases

1. **`X / X`** (anchor equals alignment, both non-center — e.g. `N / N`,
   `NE / NE`, `S / S`): Desktop draws the label sitting **on top of** the
   node — the label's `X` corner coincides with the node's `X` corner, so
   the label center ends up near the node center. The current converter
   places the label outside the node in direction `X`; the rule says draw
   it **at the node center** (`anchor = (c, c)`). All 8 such combinations
   are wrong.
2. **`C / X`** (center anchor, non-center alignment — e.g. `C / NW`):
   Desktop hangs the label off the node center in the **opposite**
   direction of `X`. The current converter leaves the ANCHOR at center, so
   Cytoscape.js draws the label over the node; the rule says set the
   ANCHOR to the **complement** of `X`, so the label is placed on the
   opposite side. All 8 such combinations are wrong.

Together these two patterns account for 16 of the 56 corrections; the
other 40 are mixed cases where one or both axes need adjustment.

### Suggested replacements (56 rows)

Only combinations where the rule disagrees with the current converter are
listed. The 25 omitted combinations (the 9 complementary pairs plus 16
"directionally already correct" cases) need no change — they are already
on the closest CSj direction the rule could pick. ALIGN, JUSTIFICATION
and margin fields are not listed; they are unchanged from the main fact
table.

| CX1 anchor / align | Desktop direction | Current ANCHOR | Better ANCHOR |
|---|---|---|---|
| C  / N  | below node       | (c, c) | (c, b) |
| C  / NE | lower-left       | (c, c) | (l, b) |
| C  / E  | left of node     | (c, c) | (l, c) |
| C  / SE | upper-left       | (c, c) | (l, t) |
| C  / S  | above node       | (c, c) | (c, t) |
| C  / SW | upper-right      | (c, c) | (r, t) |
| C  / W  | right of node    | (c, c) | (r, c) |
| C  / NW | lower-right      | (c, c) | (r, b) |
| N  / N  | over node center | (c, t) | (c, c) |
| N  / NE | left of node     | (c, t) | (l, c) |
| N  / E  | upper-left       | (c, t) | (l, t) |
| N  / SE | upper-left       | (c, t) | (l, t) |
| N  / SW | upper-right      | (c, t) | (r, t) |
| N  / W  | upper-right      | (c, t) | (r, t) |
| N  / NW | right of node    | (c, t) | (r, c) |
| NE / N  | right of node    | (r, t) | (r, c) |
| NE / NE | over node center | (r, t) | (c, c) |
| NE / E  | above node       | (r, t) | (c, t) |
| NE / SE | above node       | (r, t) | (c, t) |
| NE / NW | right of node    | (r, t) | (r, c) |
| E  / N  | lower-right      | (r, c) | (r, b) |
| E  / NE | below node       | (r, c) | (c, b) |
| E  / E  | over node center | (r, c) | (c, c) |
| E  / SE | above node       | (r, c) | (c, t) |
| E  / S  | upper-right      | (r, c) | (r, t) |
| E  / SW | upper-right      | (r, c) | (r, t) |
| E  / NW | lower-right      | (r, c) | (r, b) |
| SE / NE | below node       | (r, b) | (c, b) |
| SE / E  | below node       | (r, b) | (c, b) |
| SE / SE | over node center | (r, b) | (c, c) |
| SE / S  | right of node    | (r, b) | (r, c) |
| SE / SW | right of node    | (r, b) | (r, c) |
| S  / NE | lower-left       | (c, b) | (l, b) |
| S  / E  | lower-left       | (c, b) | (l, b) |
| S  / SE | left of node     | (c, b) | (l, c) |
| S  / S  | over node center | (c, b) | (c, c) |
| S  / SW | right of node    | (c, b) | (r, c) |
| S  / W  | lower-right      | (c, b) | (r, b) |
| S  / NW | lower-right      | (c, b) | (r, b) |
| SW / SE | left of node     | (l, b) | (l, c) |
| SW / S  | left of node     | (l, b) | (l, c) |
| SW / SW | over node center | (l, b) | (c, c) |
| SW / W  | below node       | (l, b) | (c, b) |
| SW / NW | below node       | (l, b) | (c, b) |
| W  / N  | lower-left       | (l, c) | (l, b) |
| W  / NE | lower-left       | (l, c) | (l, b) |
| W  / SE | upper-left       | (l, c) | (l, t) |
| W  / S  | upper-left       | (l, c) | (l, t) |
| W  / SW | above node       | (l, c) | (c, t) |
| W  / W  | over node center | (l, c) | (c, c) |
| W  / NW | below node       | (l, c) | (c, b) |
| NW / N  | left of node     | (l, t) | (l, c) |
| NW / NE | left of node     | (l, t) | (l, c) |
| NW / SW | above node       | (l, t) | (c, t) |
| NW / W  | above node       | (l, t) | (c, t) |
| NW / NW | over node center | (l, t) | (c, c) |

### Trade-off: making the change would lose round-trippability

The current converter sets `ANCHOR ← cx1.anchor` and `ALIGN ← cx1.alignment`
verbatim, which lets `LabelPosition.toCX1String` reconstruct the exact CX1
string later. If the converter were changed to use the "better" ANCHOR
above, that round-trip would be lossy for the 56 affected combinations
(unless the original anchor/alignment were stashed elsewhere, e.g. in the
ALIGN fields).

The current implementation therefore reflects a deliberate
*preserve-the-data-faithfully* choice rather than an oversight. A
*render-as-close-as-possible-in-Cytoscape.js* choice would prefer the
rule above; the right answer depends on whether the consumer is a
round-tripping store or a Cytoscape.js renderer.

## Summary of precision

| Aspect of CX1 value | Conversion precision |
|---------------------|----------------------|
| Anchor (canonical 9 compass codes) | Lossless |
| Alignment (canonical 9 compass codes) | Lossless |
| Justification (`l`/`c`/`r`) | Lossless |
| Margins (X, Y) | Lossless within `float` precision |
| Visual rendering result | Approximate — depends on whether the consuming renderer (e.g. Cytoscape.js) honors both `*_ANCHOR` and `*_ALIGN`; CX1 vizmapper and Cytoscape.js do not have identical positioning semantics |

In short: the **field-level** conversion preserves all information present in
a well-formed CX1 label-position string. The "approximate" qualifier is at
the **rendering** level, because Cytoscape.js's native label-position model
does not separate node anchor from label alignment the way Cytoscape Desktop
does.
