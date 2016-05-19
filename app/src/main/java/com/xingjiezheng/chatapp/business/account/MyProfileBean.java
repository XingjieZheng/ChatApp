package com.xingjiezheng.chatapp.business.account;


import com.xingjiezheng.chatapp.framework.BaseBean;

import java.io.Serializable;
import java.util.List;

public class MyProfileBean extends BaseBean {
    private MyDetail data;

    public MyDetail getData() {
        return data;
    }

    public static class MyDetail implements Serializable {
        /**
         * 资料完成度
         */
        private int compliteRate;
        /**
         * 头像
         */
        private String head;
        /**
         * 0:未审核,1:审核通过,2:审核不通过
         */
        private int headStatus;
        /**
         * 0：资料没填过，可获得咖啡豆；1：资料已经填过
         */
        private int nickNameStatus;
        private String nickName;

        private int heightStatus;
        private int height;
        private String heightStr;
        private String ageRange;
        private int interestStatus;
        private int introduceStatus;
        private String introduce;
        private int announceStatus;
        private String announce;
        private int skillStatus;
        private String skillStr;
        private String company;
        private int positionStatus;
        private String position;
        private int workPlaceStatus;
        private String workPlace;
        private int graduteStatus;
        private String gradute;
        private String birthday;
        private List<String> photo;


        /**
         * 性别1：男性；2：女性
         */
        private int gender;
        private String registerTime;
        private String photoNum;
        private int isVerified;

        private int introduceCoffeeGot;
        private int announceCoffeeGot;
        private String hometown;
        private int hometownStatus;
        private String education;
        private int educationStatus;
        private String income;
        private int incomeStatus;
        private int marriage;
        /**
         * 1.2.0新增
         */
        private String voiceUrl;
        private int voiceId;
        private int voiceTime;
        private String voiceCoffeeBean;
        /**
         * 播放验证状态
         * 0审核中；1审核通过；2审核不通过
         */
        private int voiceVerified;


        public int getVoiceVerified() {
            return voiceVerified;
        }

        public void setVoiceVerified(int voiceVerified) {
            this.voiceVerified = voiceVerified;
        }

        public String getVoiceCoffeeBean() {
            return voiceCoffeeBean;
        }

        public void setVoiceCoffeeBean(String voiceCoffeeBean) {
            this.voiceCoffeeBean = voiceCoffeeBean;
        }

        public String getVoiceUrl() {
            return voiceUrl;
        }

        public void setVoiceUrl(String voiceUrl) {
            this.voiceUrl = voiceUrl;
        }

        public int getVoiceId() {
            return voiceId;
        }

        public void setVoiceId(int voiceId) {
            this.voiceId = voiceId;
        }

        public int getVoiceTime() {
            return voiceTime;
        }

        public void setVoiceTime(int voiceTime) {
            this.voiceTime = voiceTime;
        }

        public String getPhotoNum() {
            return photoNum;
        }

        public String getRegisterTime() {
            return registerTime;
        }

        public int getGender() {
            return gender;
        }

        public String getBirthday() {
            return birthday;
        }

        public String getGradute() {
            return gradute;
        }

        public boolean getGraduteStatus() {
            return graduteStatus == 1;
        }

        public String getWorkPlace() {
            return workPlace;
        }

        public boolean getWorkPlaceStatus() {
            return workPlaceStatus == 1;
        }

        public String getPosition() {
            return position;
        }

        public boolean getPositionStatus() {
            return positionStatus == 1;
        }

        public int getCompliteRate() {
            return compliteRate;
        }

        public String getHead() {
            return head;
        }

        public int getHeadStatus() {
            return headStatus;
        }

        public String getAgeRange() {
            return ageRange;
        }

        public String getCompany() {
            return company;
        }

        public String getSkillstr() {
            return skillStr;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public void setIntroduceStatus(int introduceStatus) {
            this.introduceStatus = introduceStatus;
        }

        public String getAnnounce() {
            return announce;
        }

        public boolean getSkillStatus() {
            return skillStatus == 1;
        }

        public boolean getAnnounceStatus() {
            return announceStatus == 1;
        }

        public boolean getIntroduceStatus() {
            return introduceStatus == 1;
        }

        public int getHeight() {
            return height;
        }

        public String getHeightStr() {
            return heightStr;
        }


        public boolean getInterestStatus() {
            return interestStatus == 1;
        }

        public boolean getHeightStatus() {
            return heightStatus == 1;
        }

        public boolean getNickNameStatus() {
            return nickNameStatus == 1;
        }

        public String getNickName() {
            return nickName;
        }

        public int getIntroduceInt() {
            return introduceStatus;
        }

        public int getGraduateInt() {
            return graduteStatus;
        }

        public int getHeightInt() {
            return heightStatus;
        }

        public void setNickNameStatus(int nickNameStatus) {
            this.nickNameStatus = nickNameStatus;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public void setHeightStatus(int heightStatus) {
            this.heightStatus = heightStatus;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public void setAgeRange(String ageRange) {
            this.ageRange = ageRange;
        }

        public void setAnnounceStatus(int announceStatus) {
            this.announceStatus = announceStatus;
        }

        public void setAnnounce(String announce) {
            this.announce = announce;
        }

        public void setSkillStatus(int skillStatus) {
            this.skillStatus = skillStatus;
        }

        public void setSkillStr(String skillstr) {
            this.skillStr = skillstr;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public void setPositionStatus(int positionStatus) {
            this.positionStatus = positionStatus;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public void setWorkPlaceStatus(int workPlaceStatus) {
            this.workPlaceStatus = workPlaceStatus;
        }

        public void setWorkPlace(String workPlace) {
            this.workPlace = workPlace;
        }

        public void setGraduteStatus(int graduteStatus) {
            this.graduteStatus = graduteStatus;
        }

        public void setGradute(String gradute) {
            this.gradute = gradute;
        }

        public void setPhotoNum(String photoNum) {
            this.photoNum = photoNum;
        }


        public void setInterestStatus(int interestStatus) {
            this.interestStatus = interestStatus;
        }

        public void setHeadStatus(int headStatus) {
            this.headStatus = headStatus;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public void setCompliteRate(int compliteRate) {
            this.compliteRate = compliteRate;
        }

        public int getIsVerified() {
            return isVerified;
        }


        public int getIntroduceCoffeeGot() {
            return introduceCoffeeGot;
        }

        public void setIntroduceCoffeeGot(int introduceCoffeeGot) {
            this.introduceCoffeeGot = introduceCoffeeGot;
        }

        public int getAnnounceCoffeeGot() {
            return announceCoffeeGot;
        }

        public void setAnnounceCoffeeGot(int announceCoffeeGot) {
            this.announceCoffeeGot = announceCoffeeGot;
        }

        public String getHometown() {
            return hometown;
        }

        public void setHometown(String hometown) {
            this.hometown = hometown;
        }

        public boolean getHometownStatus() {
            return hometownStatus == 1;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public boolean getEducationStatus() {
            return educationStatus == 1;
        }

        public String getIncome() {
            return income;
        }


        public void setIncome(String income) {
            this.income = income;
        }

        public boolean getIncomeStatus() {
            return incomeStatus == 1;
        }

        public int getMarriage() {
            return marriage;
        }

        public void setHometownStatus(int hometownStatus) {
            this.hometownStatus = hometownStatus;
        }

        public void setEducationStatus(int educationStatus) {
            this.educationStatus = educationStatus;
        }

        public void setIncomeStatus(int incomeStatus) {
            this.incomeStatus = incomeStatus;
        }

        public List<String> getPhoto() {
            return photo;
        }

        public void setPhoto(List<String> photo) {
            this.photo = photo;
        }
    }


}
