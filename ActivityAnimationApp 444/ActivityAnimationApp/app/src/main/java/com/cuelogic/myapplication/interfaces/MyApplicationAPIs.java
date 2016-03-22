package com.cuelogic.myapplication.interfaces;

import com.cuelogic.myapplication.models.inputmodels.LoginUserInputData;

/**
 * Created by cuelogic on 31/08/15.
 */
public interface MyApplicationAPIs {

    ///////////////////////////////////////////////////////////////////////////
    // To login the user into an application.
    ///////////////////////////////////////////////////////////////////////////
    void loginUser(FragmentResponseReceiverListener fragmentResponseReceiverListener,
                          LoginUserInputData loginUserInputData);
}
