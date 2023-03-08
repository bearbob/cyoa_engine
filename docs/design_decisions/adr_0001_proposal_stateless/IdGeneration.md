# ID Generation

A call to the API will contain an id that is build by concatenating pagekey, userkey and statekey.
The format is:
* first character: indicate the length (number of characters) following this character that are used by the pagekey
* 2..k characters: pagekey
* (k+1) character: indicate the length following this character that are used by the userkey
* (k+2)..i characters: userkey
* (i+1)..j characters: statekey

The length indicator of the statekey is not used, because it is the last key. 
Therefore, all characters after the userkey are attributed to the statekey.