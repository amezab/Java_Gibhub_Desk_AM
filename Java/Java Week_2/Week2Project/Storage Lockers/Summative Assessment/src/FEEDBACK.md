This is a good first attempt, you've come a long way. You demonstrate that you can separate concerns into different classes and you show a beginning grasp of class relationships. There's no obvious errors to congrats on making it work!

Mostly, we should clean up a bit in the code submitted to make it look more professional.

**Overall : Revision Requested**

* In `Main.java` , lockerList` is redundant since we already have `lockerService.getLockers()
* Put a space after `//` comments for better readability
* On line 76, `}io.closeScanner();` should be on separate lines
* In `IO.java`, `promptForLockerandPin()` should be `promptForLockerAndPin()` (proper camelCase)
* Missing spaces in print statements: `"Enter Locker number"` should be `"Enter Locker number: "`
* The `closeScanner()` method is empty you can remove it.
* In `Locker.java`, remove the blank lines at the end of the code.
* The unused `Objects` import can be removed.
* In `LockerAccessMG.java` , Both methods have identical validation logic, consider creating a private method to handle that and have both methods use that method instead.