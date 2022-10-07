package com.company.benefit.util

import android.content.Context
import android.util.Log
import com.igaworks.adpopcorn.Adpopcorn
import com.igaworks.adpopcorn.AdpopcornExtension
import com.igaworks.adpopcorn.cores.model.APClientRewardItem
import com.igaworks.adpopcorn.interfaces.IAPClientRewardCallbackListener
import com.igaworks.adpopcorn.interfaces.IAdPOPcornEventListener

class AdPopcornUtil(private val context: Context) {

    fun init(){

        /**
         *
         * 1. 1명의 유저는 1개의 고유한 유저식별값을 가져야하며, 가변적인 값을 사용해서는 안됩니다.
         * 2. 개인정보(이메일, 이름, 전화번호, 식별가능한 유저아이디 등)이 포함되어서는 안됩니다.
         * 3. 한글, 특수문자, 공백 등이 포함된 경우에는 반드시 URL 인코딩 처리를 하여 사용하여야 합니다.
         * 4. ADID는 유저식별값으로 사용할 수 없습니다.(암호화 후 사용 가능)
         * 5. 유저가 오퍼월에 진입하기 전에 설정되어야 합니다.
         *
         */
        Adpopcorn.setUserId(context,"suHobXlBY2NvdW50X25hbWU")
        // 잠금화면에서의 오퍼월 광고 노출
        AdpopcornExtension.useFlagShowWhenLocked(context,true)
        // 고객센터 노출
        /**
         * 오퍼월을 통해서 접근 가능한 고객센터를 앱 내 다른 경로에서 접근하고자 할 경우 사용 가능합니다.
         * 반드시, 조회하고자 하는 유저의 UserId를 정확히 입력해 주어야 해당 유저의 참여 이력이 조회됩니다
         */
        // AdpopcornExtension.openCSPage(this,"userId")



        // 리스트 형태의 오퍼월 광고 노출
        // AdpopcornExtension.openLegacyOfferWall(this);
        // 피드형 타입 오퍼월 광고 노출
        Adpopcorn.openFeedOfferWall(context)
        // 간편적립 타입 오퍼월 광고 노출
        //Adpopcorn.openCPMOfferwall(this)


        // 오퍼월 개인 정보 수집 동의 및 종료에 대한 이벤트 리스너를 제공
        Adpopcorn.setEventListener(context, object : IAdPOPcornEventListener {
            override fun OnClosedOfferWallPage() {
                Log.d("AdPopcornActivity","OnClosedOfferWallPage")
            }

            override fun OnAgreePrivacy() {
                Log.d("AdPopcornActivity","OnAgreePrivacy")
            }

            override fun OnDisagreePrivacy() {
                Log.d("AdPopcornActivity","OnDisagreePrivacy")
            }

        })

        // 오퍼월 적립 가능 리워드 정보 조회
        AdpopcornExtension.getEarnableTotalRewardInfo(context) { queryResult, totalCount, totalReward -> // queryResult : 조회결과, totalCount : 참여 가능한 캠페인 수
            // totalReward : 적립 가능한 총 리워드
            Log.d("AdPopcornActivity", "OnEarnableTotalRewardInfo")
            Log.d("AdPopcornActivity",
                "totalReward : $totalReward, queryResult : $queryResult, totalCount : $totalCount")
        }

        /**
         * 1. rewardItem[i].getCampaignKey() : 유저가 완료한 캠페인의 캠페인 키
         * 2. rewardItem[i].getCampaignTitle() : 유저가 완료한 캠페인의 이름
         * 3. rewardItem[i].getRTID() : 완료 트랜잭션 아이디
         * 4. rewardItem[i].getRewardQauntity() : 유저에게 지급해야 할 가상화폐의 양
         * 5. rewardItem[i].didGiveRewardItem() : 리워드 지급 처리 완료 정보를 IGAW서버에 전달하기 위한 api
         */

        AdpopcornExtension.setClientRewardCallbackListener(context, object :
            IAPClientRewardCallbackListener {
            override fun onGetRewardInfo(
                isSuccess: Boolean,
                resultMsg: String?,
                rewardItems: Array<out APClientRewardItem>
            ) {
                //아래 정보를 이용하여 유저에게 리워드를 지급합니다.
                rewardItems[0].campaignKey
                rewardItems[0].campaignTitle
                rewardItems[0].rtid
                rewardItems[0].rewardQuantity

                // didGiveRewardItem api를 이용하여 유효한 RewardKey 인지 확인합니다.
                // 확인결과는 onDidGiveRewardItemResult로 전달됩니다.
                rewardItems[0].didGiveRewardItem()

                Log.d("AdPopcornActivity", "campaignKey : ${rewardItems[0].campaignKey} / " +
                        "campaignTitle : ${rewardItems[0].campaignTitle} / " +
                        "rtid : ${rewardItems[0].rtid} / " +
                        "rewardQuantity : ${rewardItems[0].rewardQuantity} / " +
                        "didGiveRewardItem : ${rewardItems[0].didGiveRewardItem()} / ")

            }

            override fun onDidGiveRewardItemResult(isSuccess: Boolean, resultMsg: String?, resultCode: Int, completedRewardKey: String?) {
                // 본 이벤트리스너의 수신 결과가 성공일 때에만 유저에게 리워드 지급 처리를 합니다.
                // 한번 지급 처리한 completedRewardKey 에 대해서는 다시 리워드 지급을 하면 안됩니다.
                Log.d("AdPopcornActivity", "isSuccess : $isSuccess / " +
                        "resultMsg : $resultMsg / " +
                        "resultCode : $resultCode / " +
                        "completedRewardKey : $completedRewardKey / ")
            }

        })

    }

}