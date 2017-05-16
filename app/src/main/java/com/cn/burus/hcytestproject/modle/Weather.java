package com.cn.burus.hcytestproject.modle;

import android.os.Parcel;
import android.os.Parcelable;

import com.cn.burus.hcytestproject.httpframework.error.AppException;
import com.cn.burus.hcytestproject.httpframework.itf.JsonReaderable;
import com.google.gson.stream.JsonReader;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengyou.huang on 2017/1/9.
 */

public class Weather implements JsonReaderable {

    private List<HeWeatherUserHH> HeWeather;

    public List<HeWeatherUserHH> getHeWeather() {
        return HeWeather;
    }

    public void setHeWeather(List<HeWeatherUserHH> HeWeather) {
        this.HeWeather = HeWeather;
    }

    @Override
    public void readFromJson(JsonReader reader) throws AppException {
        KLog.i("Weather:", "---" + reader.toString());
    }

    public static class HeWeatherUserHH implements Parcelable {
        /**
         * aqi : {"city":{"aqi":"53","co":"1","no2":"29","o3":"100","pm10":"54","pm25":"33","qlty":"良","so2":"8"}}
         * basic : {"city":"深圳","cnty":"中国","id":"CN101280601","lat":"22.544000","lon":"114.109000","update":{"loc":"2017-01-09 13:52","utc":"2017-01-09 05:52"}}
         * daily_forecast : [{"astro":{"mr":"14:58","ms":"03:23","sr":"07:05","ss":"17:56"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2017-01-09","hum":"73","pcpn":"0.0","pop":"0","pres":"1016","tmp":{"max":"23","min":"16"},"uv":"7","vis":"10","wind":{"deg":"77","dir":"无持续风向","sc":"微风","spd":"2"}},{"astro":{"mr":"15:54","ms":"04:26","sr":"07:05","ss":"17:56"},"cond":{"code_d":"104","code_n":"104","txt_d":"阴","txt_n":"阴"},"date":"2017-01-10","hum":"78","pcpn":"0.0","pop":"1","pres":"1018","tmp":{"max":"22","min":"16"},"uv":"7","vis":"10","wind":{"deg":"99","dir":"无持续风向","sc":"微风","spd":"1"}},{"astro":{"mr":"16:53","ms":"05:30","sr":"07:05","ss":"17:57"},"cond":{"code_d":"305","code_n":"305","txt_d":"小雨","txt_n":"小雨"},"date":"2017-01-11","hum":"69","pcpn":"0.0","pop":"27","pres":"1018","tmp":{"max":"21","min":"16"},"uv":"6","vis":"10","wind":{"deg":"81","dir":"无持续风向","sc":"微风","spd":"4"}}]
         * hourly_forecast : [{"cond":{"code":"103","txt":"晴间多云"},"date":"2017-01-09 16:00","hum":"62","pop":"0","pres":"1015","tmp":"25","wind":{"deg":"106","dir":"东南风","sc":"微风","spd":"8"}},{"cond":{"code":"100","txt":"晴"},"date":"2017-01-09 19:00","hum":"78","pop":"0","pres":"1016","tmp":"24","wind":{"deg":"118","dir":"东南风","sc":"微风","spd":"9"}},{"cond":{"code":"100","txt":"晴"},"date":"2017-01-09 22:00","hum":"89","pop":"0","pres":"1017","tmp":"21","wind":{"deg":"109","dir":"东南风","sc":"微风","spd":"9"}}]
         * now : {"cond":{"code":"101","txt":"多云"},"fl":"25","hum":"61","pcpn":"0","pres":"1016","tmp":"23","vis":"10","wind":{"deg":"60","dir":"北风","sc":"3-4","spd":"11"}}
         * status : ok
         * suggestion : {"air":{"brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"},"comf":{"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"},"cw":{"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"drsg":{"brf":"较舒适","txt":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"},"flu":{"brf":"较易发","txt":"相对今天出现了较大幅度降温，较易发生感冒，体质较弱的朋友请注意适当防护。"},"sport":{"brf":"较适宜","txt":"天气较好，但考虑气温较低，推荐您进行室内运动，若户外适当增减衣物并注意防晒。"},"trav":{"brf":"适宜","txt":"天气较好，但丝毫不会影响您出行的心情。温度适宜又有微风相伴，适宜旅游。"},"uv":{"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}}
         */

        private AqiUserHH aqi;
        private BasicUserHH basic;
        private NowUserHH now;
        private String status;
        private SuggestionUserHH suggestion;
        private List<DailyForecastUserHH> daily_forecast;
        private List<HourlyForecastUserHH> hourly_forecast;

        public AqiUserHH getAqi() {
            return aqi;
        }

        public void setAqi(AqiUserHH aqi) {
            this.aqi = aqi;
        }

        public BasicUserHH getBasic() {
            return basic;
        }

        public void setBasic(BasicUserHH basic) {
            this.basic = basic;
        }

        public NowUserHH getNow() {
            return now;
        }

        public void setNow(NowUserHH now) {
            this.now = now;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public SuggestionUserHH getSuggestion() {
            return suggestion;
        }

        public void setSuggestion(SuggestionUserHH suggestion) {
            this.suggestion = suggestion;
        }

        public List<DailyForecastUserHH> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastUserHH> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public List<HourlyForecastUserHH> getHourly_forecast() {
            return hourly_forecast;
        }

        public void setHourly_forecast(List<HourlyForecastUserHH> hourly_forecast) {
            this.hourly_forecast = hourly_forecast;
        }

        public static class AqiUserHH implements Parcelable {
            /**
             * city : {"aqi":"53","co":"1","no2":"29","o3":"100","pm10":"54","pm25":"33","qlty":"良","so2":"8"}
             */

            private CityUserHH city;

            public CityUserHH getCity() {
                return city;
            }

            public void setCity(CityUserHH city) {
                this.city = city;
            }

            public static class CityUserHH implements Parcelable {
                /**
                 * aqi : 53
                 * co : 1
                 * no2 : 29
                 * o3 : 100
                 * pm10 : 54
                 * pm25 : 33
                 * qlty : 良
                 * so2 : 8
                 */

                private String aqi;
                private String co;
                private String no2;
                private String o3;
                private String pm10;
                private String pm25;
                private String qlty;
                private String so2;

                public String getAqi() {
                    return aqi;
                }

                public void setAqi(String aqi) {
                    this.aqi = aqi;
                }

                public String getCo() {
                    return co;
                }

                public void setCo(String co) {
                    this.co = co;
                }

                public String getNo2() {
                    return no2;
                }

                public void setNo2(String no2) {
                    this.no2 = no2;
                }

                public String getO3() {
                    return o3;
                }

                public void setO3(String o3) {
                    this.o3 = o3;
                }

                public String getPm10() {
                    return pm10;
                }

                public void setPm10(String pm10) {
                    this.pm10 = pm10;
                }

                public String getPm25() {
                    return pm25;
                }

                public void setPm25(String pm25) {
                    this.pm25 = pm25;
                }

                public String getQlty() {
                    return qlty;
                }

                public void setQlty(String qlty) {
                    this.qlty = qlty;
                }

                public String getSo2() {
                    return so2;
                }

                public void setSo2(String so2) {
                    this.so2 = so2;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.aqi);
                    dest.writeString(this.co);
                    dest.writeString(this.no2);
                    dest.writeString(this.o3);
                    dest.writeString(this.pm10);
                    dest.writeString(this.pm25);
                    dest.writeString(this.qlty);
                    dest.writeString(this.so2);
                }

                public CityUserHH() {
                }

                protected CityUserHH(Parcel in) {
                    this.aqi = in.readString();
                    this.co = in.readString();
                    this.no2 = in.readString();
                    this.o3 = in.readString();
                    this.pm10 = in.readString();
                    this.pm25 = in.readString();
                    this.qlty = in.readString();
                    this.so2 = in.readString();
                }

                public static final Creator<CityUserHH> CREATOR = new Creator<CityUserHH>() {
                    @Override
                    public CityUserHH createFromParcel(Parcel source) {
                        return new CityUserHH(source);
                    }

                    @Override
                    public CityUserHH[] newArray(int size) {
                        return new CityUserHH[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeParcelable(this.city, flags);
            }

            public AqiUserHH() {
            }

            protected AqiUserHH(Parcel in) {
                this.city = in.readParcelable(CityUserHH.class.getClassLoader());
            }

            public static final Parcelable.Creator<AqiUserHH> CREATOR = new Parcelable.Creator<AqiUserHH>() {
                @Override
                public AqiUserHH createFromParcel(Parcel source) {
                    return new AqiUserHH(source);
                }

                @Override
                public AqiUserHH[] newArray(int size) {
                    return new AqiUserHH[size];
                }
            };
        }

        public static class BasicUserHH implements Parcelable {
            /**
             * city : 深圳
             * cnty : 中国
             * id : CN101280601
             * lat : 22.544000
             * lon : 114.109000
             * update : {"loc":"2017-01-09 13:52","utc":"2017-01-09 05:52"}
             */

            private String city;
            private String cnty;
            private String id;
            private String lat;
            private String lon;
            private UpdateUserHH update;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public UpdateUserHH getUpdate() {
                return update;
            }

            public void setUpdate(UpdateUserHH update) {
                this.update = update;
            }

            public static class UpdateUserHH implements Parcelable {
                /**
                 * loc : 2017-01-09 13:52
                 * utc : 2017-01-09 05:52
                 */

                private String loc;
                private String utc;

                public String getLoc() {
                    return loc;
                }

                public void setLoc(String loc) {
                    this.loc = loc;
                }

                public String getUtc() {
                    return utc;
                }

                public void setUtc(String utc) {
                    this.utc = utc;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.loc);
                    dest.writeString(this.utc);
                }

                public UpdateUserHH() {
                }

                protected UpdateUserHH(Parcel in) {
                    this.loc = in.readString();
                    this.utc = in.readString();
                }

                public static final Creator<UpdateUserHH> CREATOR = new Creator<UpdateUserHH>() {
                    @Override
                    public UpdateUserHH createFromParcel(Parcel source) {
                        return new UpdateUserHH(source);
                    }

                    @Override
                    public UpdateUserHH[] newArray(int size) {
                        return new UpdateUserHH[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.city);
                dest.writeString(this.cnty);
                dest.writeString(this.id);
                dest.writeString(this.lat);
                dest.writeString(this.lon);
                dest.writeParcelable(this.update, flags);
            }

            public BasicUserHH() {
            }

            protected BasicUserHH(Parcel in) {
                this.city = in.readString();
                this.cnty = in.readString();
                this.id = in.readString();
                this.lat = in.readString();
                this.lon = in.readString();
                this.update = in.readParcelable(UpdateUserHH.class.getClassLoader());
            }

            public static final Creator<BasicUserHH> CREATOR = new Creator<BasicUserHH>() {
                @Override
                public BasicUserHH createFromParcel(Parcel source) {
                    return new BasicUserHH(source);
                }

                @Override
                public BasicUserHH[] newArray(int size) {
                    return new BasicUserHH[size];
                }
            };
        }

        public static class NowUserHH implements Parcelable {
            /**
             * cond : {"code":"101","txt":"多云"}
             * fl : 25
             * hum : 61
             * pcpn : 0
             * pres : 1016
             * tmp : 23
             * vis : 10
             * wind : {"deg":"60","dir":"北风","sc":"3-4","spd":"11"}
             */

            private CondUserHH cond;
            private String fl;
            private String hum;
            private String pcpn;
            private String pres;
            private String tmp;
            private String vis;
            private WindUserHH wind;

            public CondUserHH getCond() {
                return cond;
            }

            public void setCond(CondUserHH cond) {
                this.cond = cond;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindUserHH getWind() {
                return wind;
            }

            public void setWind(WindUserHH wind) {
                this.wind = wind;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeParcelable(this.cond, flags);
                dest.writeString(this.fl);
                dest.writeString(this.hum);
                dest.writeString(this.pcpn);
                dest.writeString(this.pres);
                dest.writeString(this.tmp);
                dest.writeString(this.vis);
                dest.writeParcelable(this.wind, flags);
            }

            public NowUserHH() {
            }

            protected NowUserHH(Parcel in) {
                this.cond = in.readParcelable(CondUserHH.class.getClassLoader());
                this.fl = in.readString();
                this.hum = in.readString();
                this.pcpn = in.readString();
                this.pres = in.readString();
                this.tmp = in.readString();
                this.vis = in.readString();
                this.wind = in.readParcelable(WindUserHH.class.getClassLoader());
            }

            public static final Creator<NowUserHH> CREATOR = new Creator<NowUserHH>() {
                @Override
                public NowUserHH createFromParcel(Parcel source) {
                    return new NowUserHH(source);
                }

                @Override
                public NowUserHH[] newArray(int size) {
                    return new NowUserHH[size];
                }
            };
        }

        public static class SuggestionUserHH implements Parcelable {
            /**
             * air : {"brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"}
             * comf : {"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"}
             * cw : {"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"}
             * drsg : {"brf":"较舒适","txt":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"}
             * flu : {"brf":"较易发","txt":"相对今天出现了较大幅度降温，较易发生感冒，体质较弱的朋友请注意适当防护。"}
             * sport : {"brf":"较适宜","txt":"天气较好，但考虑气温较低，推荐您进行室内运动，若户外适当增减衣物并注意防晒。"}
             * trav : {"brf":"适宜","txt":"天气较好，但丝毫不会影响您出行的心情。温度适宜又有微风相伴，适宜旅游。"}
             * uv : {"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}
             */

            private AirUserHH air;
            private AirUserHH comf;
            private AirUserHH cw;
            private AirUserHH drsg;
            private AirUserHH flu;
            private AirUserHH sport;
            private AirUserHH trav;
            private AirUserHH uv;

            public AirUserHH getAir() {
                return air;
            }

            public void setAir(AirUserHH air) {
                this.air = air;
            }

            public AirUserHH getComf() {
                return comf;
            }

            public void setComf(AirUserHH comf) {
                this.comf = comf;
            }

            public AirUserHH getCw() {
                return cw;
            }

            public void setCw(AirUserHH cw) {
                this.cw = cw;
            }

            public AirUserHH getDrsg() {
                return drsg;
            }

            public void setDrsg(AirUserHH drsg) {
                this.drsg = drsg;
            }

            public AirUserHH getFlu() {
                return flu;
            }

            public void setFlu(AirUserHH flu) {
                this.flu = flu;
            }

            public AirUserHH getSport() {
                return sport;
            }

            public void setSport(AirUserHH sport) {
                this.sport = sport;
            }

            public AirUserHH getTrav() {
                return trav;
            }

            public void setTrav(AirUserHH trav) {
                this.trav = trav;
            }

            public AirUserHH getUv() {
                return uv;
            }

            public void setUv(AirUserHH uv) {
                this.uv = uv;
            }

            public static class AirUserHH implements Parcelable {
                /**
                 * brf : 中
                 * txt : 气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。
                 */

                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.brf);
                    dest.writeString(this.txt);
                }

                public AirUserHH() {
                }

                protected AirUserHH(Parcel in) {
                    this.brf = in.readString();
                    this.txt = in.readString();
                }

                public static final Creator<AirUserHH> CREATOR = new Creator<AirUserHH>() {
                    @Override
                    public AirUserHH createFromParcel(Parcel source) {
                        return new AirUserHH(source);
                    }

                    @Override
                    public AirUserHH[] newArray(int size) {
                        return new AirUserHH[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeParcelable(this.air, flags);
                dest.writeParcelable(this.comf, flags);
                dest.writeParcelable(this.cw, flags);
                dest.writeParcelable(this.drsg, flags);
                dest.writeParcelable(this.flu, flags);
                dest.writeParcelable(this.sport, flags);
                dest.writeParcelable(this.trav, flags);
                dest.writeParcelable(this.uv, flags);
            }

            public SuggestionUserHH() {
            }

            protected SuggestionUserHH(Parcel in) {
                this.air = in.readParcelable(AirUserHH.class.getClassLoader());
                this.comf = in.readParcelable(AirUserHH.class.getClassLoader());
                this.cw = in.readParcelable(AirUserHH.class.getClassLoader());
                this.drsg = in.readParcelable(AirUserHH.class.getClassLoader());
                this.flu = in.readParcelable(AirUserHH.class.getClassLoader());
                this.sport = in.readParcelable(AirUserHH.class.getClassLoader());
                this.trav = in.readParcelable(AirUserHH.class.getClassLoader());
                this.uv = in.readParcelable(AirUserHH.class.getClassLoader());
            }

            public static final Creator<SuggestionUserHH> CREATOR = new Creator<SuggestionUserHH>() {
                @Override
                public SuggestionUserHH createFromParcel(Parcel source) {
                    return new SuggestionUserHH(source);
                }

                @Override
                public SuggestionUserHH[] newArray(int size) {
                    return new SuggestionUserHH[size];
                }
            };
        }

        public static class DailyForecastUserHH implements Parcelable {
            /**
             * astro : {"mr":"14:58","ms":"03:23","sr":"07:05","ss":"17:56"}
             * cond : {"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"}
             * date : 2017-01-09
             * hum : 73
             * pcpn : 0.0
             * pop : 0
             * pres : 1016
             * tmp : {"max":"23","min":"16"}
             * uv : 7
             * vis : 10
             * wind : {"deg":"77","dir":"无持续风向","sc":"微风","spd":"2"}
             */

            private AstroUserHH astro;
            private CondUserHHX cond;
            private String date;
            private String hum;
            private String pcpn;
            private String pop;
            private String pres;
            private TmpUserHH tmp;
            private String uv;
            private String vis;
            private WindUserHH wind;

            public AstroUserHH getAstro() {
                return astro;
            }

            public void setAstro(AstroUserHH astro) {
                this.astro = astro;
            }

            public CondUserHHX getCond() {
                return cond;
            }

            public void setCond(CondUserHHX cond) {
                this.cond = cond;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public TmpUserHH getTmp() {
                return tmp;
            }

            public void setTmp(TmpUserHH tmp) {
                this.tmp = tmp;
            }

            public String getUv() {
                return uv;
            }

            public void setUv(String uv) {
                this.uv = uv;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindUserHH getWind() {
                return wind;
            }

            public void setWind(WindUserHH wind) {
                this.wind = wind;
            }

            public static class AstroUserHH implements Parcelable {
                /**
                 * mr : 14:58
                 * ms : 03:23
                 * sr : 07:05
                 * ss : 17:56
                 */

                private String mr;
                private String ms;
                private String sr;
                private String ss;

                public String getMr() {
                    return mr;
                }

                public void setMr(String mr) {
                    this.mr = mr;
                }

                public String getMs() {
                    return ms;
                }

                public void setMs(String ms) {
                    this.ms = ms;
                }

                public String getSr() {
                    return sr;
                }

                public void setSr(String sr) {
                    this.sr = sr;
                }

                public String getSs() {
                    return ss;
                }

                public void setSs(String ss) {
                    this.ss = ss;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.mr);
                    dest.writeString(this.ms);
                    dest.writeString(this.sr);
                    dest.writeString(this.ss);
                }

                public AstroUserHH() {
                }

                protected AstroUserHH(Parcel in) {
                    this.mr = in.readString();
                    this.ms = in.readString();
                    this.sr = in.readString();
                    this.ss = in.readString();
                }

                public static final Creator<AstroUserHH> CREATOR = new Creator<AstroUserHH>() {
                    @Override
                    public AstroUserHH createFromParcel(Parcel source) {
                        return new AstroUserHH(source);
                    }

                    @Override
                    public AstroUserHH[] newArray(int size) {
                        return new AstroUserHH[size];
                    }
                };
            }

            public static class CondUserHHX implements Parcelable {
                /**
                 * code_d : 101
                 * code_n : 101
                 * txt_d : 多云
                 * txt_n : 多云
                 */

                private String code_d;
                private String code_n;
                private String txt_d;
                private String txt_n;

                public String getCode_d() {
                    return code_d;
                }

                public void setCode_d(String code_d) {
                    this.code_d = code_d;
                }

                public String getCode_n() {
                    return code_n;
                }

                public void setCode_n(String code_n) {
                    this.code_n = code_n;
                }

                public String getTxt_d() {
                    return txt_d;
                }

                public void setTxt_d(String txt_d) {
                    this.txt_d = txt_d;
                }

                public String getTxt_n() {
                    return txt_n;
                }

                public void setTxt_n(String txt_n) {
                    this.txt_n = txt_n;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.code_d);
                    dest.writeString(this.code_n);
                    dest.writeString(this.txt_d);
                    dest.writeString(this.txt_n);
                }

                public CondUserHHX() {
                }

                protected CondUserHHX(Parcel in) {
                    this.code_d = in.readString();
                    this.code_n = in.readString();
                    this.txt_d = in.readString();
                    this.txt_n = in.readString();
                }

                public static final Creator<CondUserHHX> CREATOR = new Creator<CondUserHHX>() {
                    @Override
                    public CondUserHHX createFromParcel(Parcel source) {
                        return new CondUserHHX(source);
                    }

                    @Override
                    public CondUserHHX[] newArray(int size) {
                        return new CondUserHHX[size];
                    }
                };
            }

            public static class TmpUserHH implements Parcelable {
                /**
                 * max : 23
                 * min : 16
                 */

                private String max;
                private String min;

                public String getMax() {
                    return max;
                }

                public void setMax(String max) {
                    this.max = max;
                }

                public String getMin() {
                    return min;
                }

                public void setMin(String min) {
                    this.min = min;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.max);
                    dest.writeString(this.min);
                }

                public TmpUserHH() {
                }

                protected TmpUserHH(Parcel in) {
                    this.max = in.readString();
                    this.min = in.readString();
                }

                public static final Creator<TmpUserHH> CREATOR = new Creator<TmpUserHH>() {
                    @Override
                    public TmpUserHH createFromParcel(Parcel source) {
                        return new TmpUserHH(source);
                    }

                    @Override
                    public TmpUserHH[] newArray(int size) {
                        return new TmpUserHH[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeParcelable(this.astro, flags);
                dest.writeParcelable(this.cond, flags);
                dest.writeString(this.date);
                dest.writeString(this.hum);
                dest.writeString(this.pcpn);
                dest.writeString(this.pop);
                dest.writeString(this.pres);
                dest.writeParcelable(this.tmp, flags);
                dest.writeString(this.uv);
                dest.writeString(this.vis);
                dest.writeParcelable(this.wind, flags);
            }

            public DailyForecastUserHH() {
            }

            protected DailyForecastUserHH(Parcel in) {
                this.astro = in.readParcelable(AstroUserHH.class.getClassLoader());
                this.cond = in.readParcelable(CondUserHHX.class.getClassLoader());
                this.date = in.readString();
                this.hum = in.readString();
                this.pcpn = in.readString();
                this.pop = in.readString();
                this.pres = in.readString();
                this.tmp = in.readParcelable(TmpUserHH.class.getClassLoader());
                this.uv = in.readString();
                this.vis = in.readString();
                this.wind = in.readParcelable(WindUserHH.class.getClassLoader());
            }

            public static final Creator<DailyForecastUserHH> CREATOR = new Creator<DailyForecastUserHH>() {
                @Override
                public DailyForecastUserHH createFromParcel(Parcel source) {
                    return new DailyForecastUserHH(source);
                }

                @Override
                public DailyForecastUserHH[] newArray(int size) {
                    return new DailyForecastUserHH[size];
                }
            };
        }

        public static class HourlyForecastUserHH implements Parcelable {
            /**
             * cond : {"code":"103","txt":"晴间多云"}
             * date : 2017-01-09 16:00
             * hum : 62
             * pop : 0
             * pres : 1015
             * tmp : 25
             * wind : {"deg":"106","dir":"东南风","sc":"微风","spd":"8"}
             */

            private CondUserHH cond;
            private String date;
            private String hum;
            private String pop;
            private String pres;
            private String tmp;
            private WindUserHH wind;

            public CondUserHH getCond() {
                return cond;
            }

            public void setCond(CondUserHH cond) {
                this.cond = cond;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public WindUserHH getWind() {
                return wind;
            }

            public void setWind(WindUserHH wind) {
                this.wind = wind;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeParcelable(this.cond, flags);
                dest.writeString(this.date);
                dest.writeString(this.hum);
                dest.writeString(this.pop);
                dest.writeString(this.pres);
                dest.writeString(this.tmp);
                dest.writeParcelable(this.wind, flags);
            }

            public HourlyForecastUserHH() {
            }

            protected HourlyForecastUserHH(Parcel in) {
                this.cond = in.readParcelable(CondUserHH.class.getClassLoader());
                this.date = in.readString();
                this.hum = in.readString();
                this.pop = in.readString();
                this.pres = in.readString();
                this.tmp = in.readString();
                this.wind = in.readParcelable(WindUserHH.class.getClassLoader());
            }

            public static final Creator<HourlyForecastUserHH> CREATOR = new Creator<HourlyForecastUserHH>() {
                @Override
                public HourlyForecastUserHH createFromParcel(Parcel source) {
                    return new HourlyForecastUserHH(source);
                }

                @Override
                public HourlyForecastUserHH[] newArray(int size) {
                    return new HourlyForecastUserHH[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(this.aqi, flags);
            dest.writeParcelable(this.basic, flags);
            dest.writeParcelable(this.now, flags);
            dest.writeString(this.status);
            dest.writeParcelable(this.suggestion, flags);
            dest.writeList(this.daily_forecast);
            dest.writeList(this.hourly_forecast);
        }

        public HeWeatherUserHH() {
        }

        protected HeWeatherUserHH(Parcel in) {
            this.aqi = in.readParcelable(AqiUserHH.class.getClassLoader());
            this.basic = in.readParcelable(BasicUserHH.class.getClassLoader());
            this.now = in.readParcelable(NowUserHH.class.getClassLoader());
            this.status = in.readString();
            this.suggestion = in.readParcelable(SuggestionUserHH.class.getClassLoader());
            this.daily_forecast = new ArrayList<DailyForecastUserHH>();
            in.readList(this.daily_forecast, DailyForecastUserHH.class.getClassLoader());
            this.hourly_forecast = new ArrayList<HourlyForecastUserHH>();
            in.readList(this.hourly_forecast, HourlyForecastUserHH.class.getClassLoader());
        }

        public static final Parcelable.Creator<HeWeatherUserHH> CREATOR = new Parcelable.Creator<HeWeatherUserHH>() {
            @Override
            public HeWeatherUserHH createFromParcel(Parcel source) {
                return new HeWeatherUserHH(source);
            }

            @Override
            public HeWeatherUserHH[] newArray(int size) {
                return new HeWeatherUserHH[size];
            }
        };
    }

    public static class CondUserHH implements Parcelable {
        /**
         * code : 101
         * txt : 多云
         */

        private String code;
        private String txt;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.code);
            dest.writeString(this.txt);
        }

        public CondUserHH() {
        }

        protected CondUserHH(Parcel in) {
            this.code = in.readString();
            this.txt = in.readString();
        }

        public static final Parcelable.Creator<CondUserHH> CREATOR = new Parcelable.Creator<CondUserHH>() {
            @Override
            public CondUserHH createFromParcel(Parcel source) {
                return new CondUserHH(source);
            }

            @Override
            public CondUserHH[] newArray(int size) {
                return new CondUserHH[size];
            }
        };
    }

    public static class WindUserHH implements Parcelable {
        /**
         * deg : 60
         * dir : 北风
         * sc : 3-4
         * spd : 11
         */

        private String deg;
        private String dir;
        private String sc;
        private String spd;

        public String getDeg() {
            return deg;
        }

        public void setDeg(String deg) {
            this.deg = deg;
        }

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public String getSc() {
            return sc;
        }

        public void setSc(String sc) {
            this.sc = sc;
        }

        public String getSpd() {
            return spd;
        }

        public void setSpd(String spd) {
            this.spd = spd;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.deg);
            dest.writeString(this.dir);
            dest.writeString(this.sc);
            dest.writeString(this.spd);
        }

        public WindUserHH() {
        }

        protected WindUserHH(Parcel in) {
            this.deg = in.readString();
            this.dir = in.readString();
            this.sc = in.readString();
            this.spd = in.readString();
        }

        public static final Parcelable.Creator<WindUserHH> CREATOR = new Parcelable.Creator<WindUserHH>() {
            @Override
            public WindUserHH createFromParcel(Parcel source) {
                return new WindUserHH(source);
            }

            @Override
            public WindUserHH[] newArray(int size) {
                return new WindUserHH[size];
            }
        };
    }
}
