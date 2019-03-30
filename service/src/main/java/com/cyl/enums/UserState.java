package com.cyl.enums;

/**
 * Created by cyl on 2019-03-30.
 */
public enum UserState {
    /*正常*/
    NORMAL(0),
    /*锁定*/
    LOCK(1);
    private Integer state;

    public Integer getState() {
        return state;
    }

    UserState(Integer state) {

        this.state = state;
    }
}
