package com.app.recylercard.adapter.bean;

import java.util.List;

/**
 * @author: liyabin
 * @description:
 * @projectName: RecylerCard
 * @date: 2016-09-24
 * @time: 16:16
 */

public class Root {

    private String reason;
    private Result result;
    private int error_code;

    public void setReason(String reason){
        this.reason = reason;
    }
    public String getReason(){
        return reason;
    }
    public void setResult(Result result){
        this.result = result;
    }
    public Result getResult(){
        return result;
    }
    public void setError_code(int error_code){
        this.error_code = error_code;
    }
    public int getError_code(){
        return error_code;
    }

    public class Result {
        private List<ListObject> list ;

        private int totalPage;

        private int ps;

        private int pno;

        public void setList(List<ListObject> list){
            this.list = list;
        }
        public List<ListObject> getList(){
            return list;
        }
        public void setTotalPage(int totalPage){
            this.totalPage = totalPage;
        }
        public int getTotalPage(){
            return totalPage;
        }
        public void setPs(int ps){
            this.ps = ps;
        }
        public int getPs(){
            return ps;
        }
        public void setPno(int pno){
            this.pno = pno;
        }
        public int getPno(){
            return pno;
        }

    }

    public class ListObject {
        private String firstImg;

        private String id;

        private String source;

        private String title;

        private String url;

        private String mark;

        public void setFirstImg(String firstImg){
            this.firstImg = firstImg;
        }
        public String getFirstImg(){
            return firstImg;
        }
        public void setId(String id){
            this.id = id;
        }
        public String getId(){
            return id;
        }
        public void setSource(String source){
            this.source = source;
        }
        public String getSource(){
            return source;
        }
        public void setTitle(String title){
            this.title = title;
        }
        public String getTitle(){
            return title;
        }
        public void setUrl(String url){
            this.url = url;
        }
        public String getUrl(){
            return url;
        }
        public void setMark(String mark){
            this.mark = mark;
        }
        public String getMark(){
            return mark;
        }

    }

}
