package com.example.android.cryptocomp;

/**
 * Created by Kayode Agboola on 22-Oct-17.
 */

/** "curency" was used in place of "currency" because currency
 * already exists in java library to avoid conflicts
 */
public class Curency {

    //ETH country price strings
    private String mEthCurrency;

    //BTC country price strings
    private String mBtcCurrency;

    //ETH country tag strings
    private int mEthTag;

    //BTC country tag strings
    private int mBtcTag;

    /**
     * Constructs a new Curency object.
     *
     * @param ethTag is the eth currency tag
     * @param btcTag is the btc currency tag
     * @param ethCurrency is the eth price
     * @param btcCurrency is the btc price
     */
    public Curency(int ethTag, String ethCurrency, int btcTag, String btcCurrency){
        mEthTag = ethTag;
        mEthCurrency = ethCurrency;
        mBtcTag = btcTag;
        mBtcCurrency = btcCurrency;
    }

    //methods returns various tags and prices
    public int getEthTag(){
        return mEthTag;
    }

    public int getBtcTag(){
        return mBtcTag;
    }

    public String getEthCurrency() {
        return mEthCurrency;
    }

    public String getBtcCurrency() {
        return mBtcCurrency;
    }
}