package data;

public enum ModalStateData {
    VISIBLE(true),
    INVISIBLE(false);

    private boolean state;

    ModalStateData(boolean state) {
        this.state = state;
    }

    public boolean isState() {
        return state;
    }
}
