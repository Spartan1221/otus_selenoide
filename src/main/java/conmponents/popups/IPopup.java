package conmponents.popups;

import data.ModalStateData;

public interface IPopup<T> {

    T modalState(ModalStateData modalStateData);
}
