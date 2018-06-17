package com.example.kim.networkproject;

import java.util.List;

public class BookItems {
    /*
        {
            "lastBuildDate": "Wed, 30 May 2018 09:59:16 +0900",
            "total": 1731,
            "start": 1,
            "display": 10,
            "items": [
                {
                    "title": "Do it! <b>안드로이드</b> 앱 프로그래밍 (<b>안드로이드</b> 8.0 오레오 버전 반영판!)",
                    "link": "http://book.naver.com/bookdb/book_detail.php?bid=13272675",
                    "image": "http://bookthumb.phinf.naver.net/cover/132/726/13272675.jpg?type=m1&udate=20180322",
                    "author": "정재곤",
                    "price": "40000",
                    "discount": "36000",
                    "publisher": "이지스퍼블리싱",
                    "pubdate": "20180209",
                    "isbn": "1188612832 9791188612833",
                    "description": "ㆍ 7년 연속 <b>안드로이드</b> 분야 1위! 전면 개정 5판(<b>안드로이드</b> 8.0 오레오 버전 · <b>안드로이드</b> 스튜디오 3.0 반영판)이 나왔다!\n\n《Do it! <b>안드로이드</b> 앱 프로그래밍》의 다섯 번째 전면 개정판이 나왔다. 이번 개정 5판에서는 <b>안드로이드</b> 8.0 버전(오레오)에 맞춰 소스 코드 테스트를 완료했다. 특히 업그레이드된... "
                },  ...
            ]
        }
*/

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<ItemsBean> items;

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    //Getter, Setter 추가



    public static class ItemsBean {
        /***
         * title : Do it! <b>안드로이드</b> 앱 프로그래밍 (<b>안드로이드</b> 8.0 오레오 버전 반영판!)
         * link : http://book.naver.com/bookdb/book_detail.php?bid=13272675
         * image : http://bookthumb.phinf.naver.net/cover/132/726/13272675.jpg?type=m1&udate=20180322
         * author : 정재곤
         * price : 40000
         * discount : 36000
         * publisher : 이지스퍼블리싱
         * pubdate : 20180209
         * isbn : 1188612832 9791188612833
         * description : ㆍ 7년 연속 <b>안드로이드</b> 분야 1위! 전면 개정 5판(<b>안드로이드</b> 8.0 오레오 버전 · <b>안드로이드</b> 스튜디오 3.0 반영판)이 나왔다!

         《Do it! <b>안드로이드</b> 앱 프로그래밍》의 다섯 번째 전면 개정판이 나왔다. 이번 개정 5판에서는 <b>안드로이드</b> 8.0 버전(오레오)에 맞춰 소스 코드 테스트를 완료했다. 특히 업그레이드된...
         */

       /* 북아이템*/

        private String title;
        private String link;
        private String image;
        private String author;
        private String price;
        private String discount;
        private String publisher;
        private String pubDate;
        private String isbn;
        private String description;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getIsbn() {
            return isbn;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        //Getter, Setter 추가

    }
}
