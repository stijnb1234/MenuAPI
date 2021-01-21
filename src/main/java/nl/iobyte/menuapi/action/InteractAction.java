package nl.iobyte.menuapi.action;

public abstract class InteractAction extends MenuAction {

    /**
     * Set if should cancel
     * @param cancel boolean
     * @return InteractAction
     */
    public abstract InteractAction setCancel(boolean cancel);

    /**
     * Check if should cancel
     * @return boolean
     */
    public abstract boolean doCancel();

}
