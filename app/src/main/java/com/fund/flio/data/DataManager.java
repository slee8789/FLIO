package com.fund.flio.data;

import com.fund.flio.data.local.db.DbHelper;
import com.fund.flio.data.local.prefs.PreferencesHelper;
import com.fund.flio.data.remote.ApiHelper;

public interface DataManager extends PreferencesHelper, ApiHelper, DbHelper {

}
