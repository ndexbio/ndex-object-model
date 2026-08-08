# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [3.0.3] - 2026-08-08

### Fixed

- **`FileItemSummary.name` is no longer advertised as a required property.** The class is annotated `@JsonInclude(Include.NON_NULL)`, so a null `name` is dropped from the serialized payload entirely rather than emitted as `"name": null` — but the field had carried `required = true`, meaning the generated OpenAPI document was inaccurate. A network legitimately has no name in three cases: its CX2 declares no `networkAttributes` aspect, its `networkAttributes` carries no name attribute, or its CX2 failed validation (in which case `errorMessage` is populated instead). The `required` flag is dropped and the description now spells out that `name` is optional for `type=NETWORK` and absent rather than null, while remaining always present for `type=FOLDER` and `type=SHORTCUT`. Documentation only — no serialization or behavioral change, and `NdexFolder`/`NdexShortcut` keep their own required `name`. Resolves ndex-rest [pr #55](https://github.com/ndexbio/ndex-object-model/pull/55).

## [3.0.2] - 2026-07-13

### Added

- **`visibility` on folder & shortcut objects** — `NdexFolder`, `NdexShortcut`, `FolderRequest`, and `ShortcutRequest` gain a `visibility` field of type `VisibilityType` (`PUBLIC`/`PRIVATE`/`UNLISTED`). This is purely additive — the classes are otherwise unchanged — so consumers can carry a folder's or shortcut's visibility on read responses and set it on create/update requests.

### Changed

- Bumped `org.apache.commons:commons-lang3` from 3.14.0 to 3.18.0.
- cleanup tests, consistent write of temp output, added gh ci workflow for unit tests.

## [3.0.0] - 2026-05-28

First GA release of NDEx Object Model 3.0 — a major expansion of the Java object model to support the NDEx 3.x platform. Introduces a hierarchical file system model, unified file browsing types, trash lifecycle, granular sharing and ownership transfer, and a new network summary for the v3 API surface. For the full commit history of this release, refer to the [`ndex3develop`](https://github.com/ndexbio/ndex-object-model/tree/ndex3develop) branch.

### Added

- **Folder & Shortcut File System Model** — New `NdexFolder` and `NdexShortcut` domain objects (extending `NdexExternalObject`) representing the core entities of the NDEx hierarchical file system. Each carries a `parent` UUID for tree positioning, owner identity fields, and a typed `target`/`targetType` reference on shortcuts. Supporting request objects `FolderRequest`, `ShortcutRequest`, `MoveNetworksRequest`, and `ShortcutTargetStatus` provide the full CRUD and placement surface for folder and shortcut operations.

- **Unified File Item Model & Search** — `FileItemSummary` provides a single polymorphic view of any file system entity — folder, network, or shortcut — combining identity (`uuid`, `type`, `name`), ownership, visibility, permission, status flags (`isReadOnly`, `isCompleted`, `isShared`, `isValid`), and network-specific fields (`edges`, `doi`). Accompanied by `FileSearchResult`, `SimpleFileQuery`, `FileCount`, and the `FileType` enum (`NETWORK`, `FOLDER`, `SHORTCUT`), enabling uniform listing, browsing, and search across all content types.

- **Trash Lifecycle Support** — `TrashRestoreRequest` carries typed lists of network, folder, and shortcut UUIDs for batch restore operations. `NetworkSummaryV3` gains `folderId` and `showInTrash` fields so the REST layer can track folder placement and trash state directly on network summaries.

- **File Sharing & Ownership Transfer** — `SharingMemberRequest` maps sets of file UUIDs (with their `FileType`) to user UUIDs and `Permissions` levels, enabling bulk permission grants and revocations across mixed file types in a single request. `SharingSimpleRequest` covers simpler single-permission sharing. `TransferOwnershipRequest` supports batch reassignment of network ownership to a new user UUID.

- **`NetworkSummaryV3` & Expanded `VisibilityType`** — A new `NetworkSummaryV3` class independent of the v2 `NetworkSummary` hierarchy, adding `folderId`, `showInTrash`, `updatedBy`, and `cxFormat` fields alongside the full v2 summary surface. The `VisibilityType` enum gains the `UNLISTED` variant — entities searchable only by exact UUID for public users but fully searchable by permissioned users — rounding out the three-tier visibility model used across folders, networks, and shortcuts.
