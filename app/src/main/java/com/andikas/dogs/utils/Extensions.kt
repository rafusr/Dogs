package com.andikas.dogs.utils

import android.app.Activity
import android.widget.Toast

object Extensions {

    fun Activity.makeToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}