package org.ndexbio.model.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.ndexbio.model.errorcodes.ErrorCode;

/**
 *
 * @author churas
 */
public class UnauthorizedOperationExceptionTest {
	
	@Test
	public void testCreateUnVerifiedAccountErrorAccountAndEmailNull(){
		UnauthorizedOperationException uoe = UnauthorizedOperationException.createUnVerifiedAccountError(null, null);
		assertTrue(uoe.getMessage().startsWith("NDEx user account null <null> is not verified"), uoe.getMessage());
		assertEquals(uoe.getNDExError().getErrorCode(), ErrorCode.NDEx_User_Account_Not_Verified);
	}
	
	@Test
	public void testCreateUnVerifiedAccountError(){
		UnauthorizedOperationException uoe = UnauthorizedOperationException.createUnVerifiedAccountError("someuser", "yo@yo.com");
		assertTrue(uoe.getMessage().startsWith("NDEx user account someuser <yo@yo.com> is not verified"), uoe.getMessage());
		assertEquals(uoe.getNDExError().getErrorCode(), ErrorCode.NDEx_User_Account_Not_Verified);
	}
	
}
