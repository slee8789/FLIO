
package com.fund.flio.data;


import com.fund.flio.data.local.db.DbHelper;
import com.fund.flio.data.local.prefs.PreferencesHelper;
import com.fund.flio.data.model.BuyerWrapper;
import com.fund.flio.data.model.ChatRoomWrapper;
import com.fund.flio.data.model.InsertMyChatWrapper;
import com.fund.flio.data.model.Keyword;
import com.fund.flio.data.model.MessageWrapper;
import com.fund.flio.data.model.ProductWrapper;
import com.fund.flio.data.model.ProductsWrapper;
import com.fund.flio.data.model.SearchResult;
import com.fund.flio.data.model.SearchWrapper;
import com.fund.flio.data.model.User;
import com.fund.flio.data.model.body.ChatDetailBody;
import com.fund.flio.data.model.body.InsertMyChatBody;
import com.fund.flio.data.model.body.SearchBody;
import com.fund.flio.data.model.body.SendMessageBody;
import com.fund.flio.data.model.body.ChatListBody;
import com.fund.flio.data.model.body.TokenBody;
import com.fund.flio.data.remote.ApiHelper;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.rxjava3.core.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;


@Singleton
public class AppDataManager implements DataManager {


    private final PreferencesHelper mPreferences;
    private final ApiHelper mFlioApi;
    private final ApiHelper mAuthApi;
    private final DbHelper mDbHelper;

    @Inject
    public AppDataManager(PreferencesHelper preferences, @Named("flio") ApiHelper flioApi, @Named("auth") ApiHelper authApi, DbHelper dbHelper) {
        mPreferences = preferences;
        mFlioApi = flioApi;
        mAuthApi = authApi;
        mDbHelper = dbHelper;
    }

    @Override
    public String getUserId() {
        return mPreferences.getUserId();
    }

    @Override
    public void setUserId(String userId) {
        mPreferences.setUserId(userId);
    }

    @Override
    public String getUserName() {
        return mPreferences.getUserName();
    }

    @Override
    public void setUserName(String userName) {
        mPreferences.setUserName(userName);
    }

    @Override
    public String getUserImageUrl() {
        return mPreferences.getUserImageUrl();
    }

    @Override
    public void setUserImageUrl(String userImageUrl) {
        mPreferences.setUserImageUrl(userImageUrl);
    }

    @Override
    public Single<Response<User>> postAuthToken(TokenBody tokenBody) {
        return mAuthApi.postAuthToken(tokenBody);
    }

    @Override
    public Single<Response<Void>> postInsertUser(User user) {
        return mFlioApi.postInsertUser(user);
    }

    @Override
    public Single<Response<Void>> postLogoutUser(String uid) {
        return mFlioApi.postLogoutUser(uid);
    }

    @Override
    public Single<Response<Void>> sendMessage(SendMessageBody sendMessageBody) {
        return mFlioApi.sendMessage(sendMessageBody);
    }

    @Override
    public Single<Response<InsertMyChatWrapper>> insertMyChat(InsertMyChatBody insertMyChatBody) {
        return mFlioApi.insertMyChat(insertMyChatBody);
    }

    @Override
    public Single<Response<ChatRoomWrapper>> selectMyChat(ChatListBody chatListBody) {
        return mFlioApi.selectMyChat(chatListBody);
    }

    @Override
    public Single<Response<MessageWrapper>> selectMyChatDetail(ChatDetailBody chatDetailBody) {
        return mFlioApi.selectMyChatDetail(chatDetailBody);
    }

    @Override
    public Single<Response<Void>> insertProduct(RequestBody title, RequestBody content, RequestBody categoryDepth1, RequestBody categoryDepth2, RequestBody saleYn, RequestBody tag, MultipartBody.Part[] imgList, RequestBody useDate, RequestBody purchaseKind, RequestBody productPrice, RequestBody tradeKind, RequestBody boxYn, RequestBody flioYn, RequestBody brand, RequestBody purpose, RequestBody modelNo, RequestBody serialNo, RequestBody repairYn, RequestBody productRelatedUrl, RequestBody uid) {
        return mFlioApi.insertProduct(title, content, categoryDepth1, categoryDepth2, saleYn, tag, imgList, useDate, purchaseKind, productPrice, tradeKind, boxYn, flioYn, brand, purpose, modelNo, serialNo, repairYn, productRelatedUrl, uid);
    }

    @Override
    public Single<Response<ProductsWrapper>> selectProduct(String uid) {
        return mFlioApi.selectProduct(uid);
    }

    @Override
    public Single<Response<ProductWrapper>> detailProduct(String productId, String uid) {
        return mFlioApi.detailProduct(productId, uid);
    }

    @Override
    public Single<Response<ProductsWrapper>> mainProduct(String uid) {
        return mFlioApi.mainProduct(uid);
    }

    @Override
    public Single<Response<ProductsWrapper>> myPageProduct(String uid) {
        return mFlioApi.myPageProduct(uid);
    }

    @Override
    public Single<Response<ProductsWrapper>> targetProduct(String targetUid, String uid) {
        return mFlioApi.targetProduct(targetUid, uid);
    }

    @Override
    public Single<Response<ProductsWrapper>> purposeProduct(int productId, String purpose, String uid) {
        return mFlioApi.purposeProduct(productId, purpose, uid);
    }

    @Override
    public Single<Response<ProductsWrapper>> recommandProduct(String categoryDepth1, String categoryDepth2, String uid) {
        return mFlioApi.recommandProduct(categoryDepth1, categoryDepth2, uid);
    }

    @Override
    public Single<Response<ProductsWrapper>> searchProduct(String keyword, String uid) {
        return mFlioApi.searchProduct(keyword, uid);
    }

    @Override
    public Single<Response<BuyerWrapper>> targetUserList(int productId, String sourceUid) {
        return mFlioApi.targetUserList(productId, sourceUid);
    }

    @Override
    public Single<Response<Void>> targetUserUpdate(int productId, String sourceUid, String targetUid) {
        return mFlioApi.targetUserUpdate(productId, sourceUid, targetUid);
    }

    @Override
    public Single<Response<Void>> targetUserReview(int productId, String targetUid, String review) {
        return mFlioApi.targetUserReview(productId, targetUid, review);
    }

    @Override
    public Single<Response<Void>> switchFavorite(String uid, int productId) {
        return mFlioApi.switchFavorite(uid, productId);
    }

    @Override
    public Single<Response<ProductsWrapper>> selectFavorite(String uid) {
        return mFlioApi.selectFavorite(uid);
    }

    @Override
    public Single<Response<SearchWrapper>> searchKeyword(SearchBody searchBody) {
        return mFlioApi.searchKeyword(searchBody);
    }

    @Override
    public String getUserToken() {
        return mPreferences.getUserToken();
    }

    @Override
    public void setUserToken(String firebaseToken) {
        mPreferences.setUserToken(firebaseToken);
    }

    @Override
    public String getMessageToken() {
        return mPreferences.getMessageToken();
    }

    @Override
    public void setMessageToken(String messageToken) {
        mPreferences.setMessageToken(messageToken);
    }

    @Override
    public String getAuthType() {
        return mPreferences.getAuthType();
    }

    @Override
    public void setAuthType(String authType) {
        mPreferences.setAuthType(authType);
    }

    @Override
    public boolean notifyChat() {
        return mPreferences.notifyChat();
    }

    @Override
    public void setNotifyChat(boolean chat) {
        mPreferences.setNotifyChat(chat);
    }

    @Override
    public boolean notifyEvery() {
        return mPreferences.notifyEvery();
    }

    @Override
    public void setNotifyEvery(boolean all) {
        mPreferences.setNotifyEvery(all);
    }

    @Override
    public Observable<List<SearchResult>> getSearchResults() {
        return mDbHelper.getSearchResults();
    }

    @Override
    public Observable<Boolean> insertSearchResult(SearchResult searchResult) {
        return mDbHelper.insertSearchResult(searchResult);
    }

    @Override
    public Observable<Boolean> deleteSearchResult(SearchResult searchResult) {
        return mDbHelper.deleteSearchResult(searchResult);
    }

    @Override
    public Observable<Boolean> deleteAll() {
        return mDbHelper.deleteAll();
    }

    @Override
    public Observable<Keyword> isExist(String keyword) {
        return mDbHelper.isExist(keyword);
    }

    @Override
    public Observable<List<Keyword>> getKeywords() {
        return mDbHelper.getKeywords();
    }

    @Override
    public Observable<Boolean> insertKeyword(Keyword keyword) {
        return mDbHelper.insertKeyword(keyword);
    }

    @Override
    public Observable<Boolean> deleteKeyword(Keyword keyword) {
        return mDbHelper.deleteKeyword(keyword);
    }
}
