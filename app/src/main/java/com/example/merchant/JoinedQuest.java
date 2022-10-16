package com.example.merchant;

public class JoinedQuest {
    public String questId;
    public String merchantId;

    public JoinedQuest() {
    }

    public JoinedQuest(String questId, String merchantId) {
        this.questId = questId;
        this.merchantId = merchantId;
    }

    public String getQuestId() {
        return questId;
    }

    public void setQuestId(String questId) {
        this.questId = questId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
}
