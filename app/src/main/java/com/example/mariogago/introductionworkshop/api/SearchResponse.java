package com.example.mariogago.introductionworkshop.api;

import java.util.Date;
import java.util.List;

public class SearchResponse {

    public List<Event> events;

    public class Event {

        public TextField name;
        public TextField description;
        public Logo logo;
        public DateTime start;
        public DateTime end;
        public Venue venue;
        public Category category;

        public class TextField {
            public String text;
            public String html;
        }

        public class Logo {
            public String url;
        }

        public class DateTime {
            public Date local;
        }

        public class Venue {
            public String name;
        }

        public class Category {
            public String name;
        }

    }
}
