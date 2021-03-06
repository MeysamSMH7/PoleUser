package com.pol.poleuser.classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.jar.Attributes;

public class SpinnerClass {
    private ArrayAdapter arrayAdapterState, arrayAdapterCity;
    public static String StateName, CityName;
    private Spinner spnState, spnCity;
    public static int StateNameID = 0;
    public static int CityNameID = 0;

    private int StateNameIDLocal = 0;
    private int CityNameIDLocal = 0;
    private String StateNameLocal = "";
    private String CityNameLocal = "";

    private boolean editPage = false;
    SharedPreferences preferencesSpinnerClass;
    Context context;

    public static String aaa = "";

    public SpinnerClass(Context context, Spinner spnState, Spinner spnCity, boolean editPage) {
        this.context = context;
        this.spnState = spnState;
        this.spnCity = spnCity;
        this.editPage = editPage;
    }

    public void spinner() {

        spnState.setSelection(0);
        spnCity.setSelection(0);


        preferencesSpinnerClass = context.getSharedPreferences("polUser", 0);
        StateNameLocal = preferencesSpinnerClass.getString("StateName_User", "خالی");
        CityNameLocal = preferencesSpinnerClass.getString("CityName_User", "خالی");

        arrayAdapterState = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameStatePolArr);
        arrayAdapterState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnState.setAdapter(arrayAdapterState);


        spnState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                StateName = parent.getItemAtPosition(position).toString();
                StateNameID = position;
                SwitchSpinnerCity();
                arrayAdapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnCity.setAdapter(arrayAdapterCity);

                if (editPage) {
                    for (int i = 0; i < NameStatePolArr.length; i++) {
                        if (NameStatePolArr[i].equals(StateNameLocal)) {
                            StateNameIDLocal = i;
                            break;
                        }
                    }
                    spnState.setSelection(StateNameIDLocal);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CityName = parent.getItemAtPosition(position).toString();
                CityNameID = position;


                if (editPage) {

                    for (int i = 0; i < NameCity.length; i++) {
                        if (NameCity[i].equals(CityNameLocal)) {
                            CityNameIDLocal = i;
                            break;
                        }
                    }

                    spnCity.setSelection(CityNameIDLocal);
                    editPage = false;

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void SwitchSpinnerCity() {

        switch (StateName) {
            case "آذربایجان شرقی":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Alarbayejan_Shargi);
                SetNameCityForEdit(NameCity_Alarbayejan_Shargi);
                break;

            case "آذربایجان غربی":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Alarbayejan_Gharbi);
                SetNameCityForEdit(NameCity_Alarbayejan_Gharbi);
                break;

            case "اردبیل":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Arfebili);
                SetNameCityForEdit(NameCity_Arfebili);
                break;

            case "اصفهان":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Esfahan);
                SetNameCityForEdit(NameCity_Esfahan);
                break;

            case "البرز":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Alborz);
                SetNameCityForEdit(NameCity_Alborz);
                break;

            case "ایلام":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Elam);
                SetNameCityForEdit(NameCity_Elam);
                break;

            case "بوشهر":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Boshehr);
                SetNameCityForEdit(NameCity_Boshehr);
                break;

            case "تهران":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Tehran);
                SetNameCityForEdit(NameCity_Tehran);
                break;

            case "چهارمحال و بختیاری":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_ChaharMahalBakhTiari);
                SetNameCityForEdit(NameCity_ChaharMahalBakhTiari);
                break;

            case "خراسان جنوبی":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Khorasan_Jonobi);
                SetNameCityForEdit(NameCity_Khorasan_Jonobi);
                break;

            case "خراسان رضوی":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Khorasan_Razavi);
                SetNameCityForEdit(NameCity_Khorasan_Razavi);
                break;

            case "خراسان شمالی":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Khorasan_Shomali);
                SetNameCityForEdit(NameCity_Khorasan_Shomali);
                break;

            case "خوستان":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Khozestan);
                SetNameCityForEdit(NameCity_Khozestan);
                break;

            case "زنجان":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Zanjan);
                SetNameCityForEdit(NameCity_Zanjan);
                break;

            case "سمنان":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Semnan);
                SetNameCityForEdit(NameCity_Semnan);
                break;

            case "سیستان و بلوچستان":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Sistan);
                SetNameCityForEdit(NameCity_Sistan);
                break;

            case "فارس":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Fars);
                SetNameCityForEdit(NameCity_Fars);
                break;

            case "قزوین":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Ghazvin);
                SetNameCityForEdit(NameCity_Ghazvin);
                break;

            case "قم":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Qom);
                SetNameCityForEdit(NameCity_Qom);
                break;

            case "کردستان":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Kordestan);
                SetNameCityForEdit(NameCity_Kordestan);
                break;

            case "کرمان":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Kerman);
                SetNameCityForEdit(NameCity_Kerman);
                break;

            case "کرمانشاه":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_KermanShah);
                SetNameCityForEdit(NameCity_KermanShah);
                break;

            case "کهگلویه و بویراحمد":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Kohgiloye_BoirAhmad);
                SetNameCityForEdit(NameCity_Kohgiloye_BoirAhmad);
                break;

            case "گستان":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Gholestan);
                SetNameCityForEdit(NameCity_Gholestan);
                break;

            case "گیلان":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Gilan);
                SetNameCityForEdit(NameCity_Gilan);
                break;

            case "لرستان":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Lorestan);
                SetNameCityForEdit(NameCity_Lorestan);
                break;

            case "مازندران":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Mazandaran);
                SetNameCityForEdit(NameCity_Mazandaran);
                break;

            case "مرکزی":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Markazi);
                SetNameCityForEdit(NameCity_Markazi);
                break;

            case "هرمزگان":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Hormozgan);
                SetNameCityForEdit(NameCity_Hormozgan);
                break;

            case "همدان":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Hamedan);
                SetNameCityForEdit(NameCity_Hamedan);
                break;

            case "یزد":
                arrayAdapterCity = new ArrayAdapter(context, android.R.layout.simple_spinner_item, NameCity_Yazd);
                SetNameCityForEdit(NameCity_Yazd);
                break;
        }
    }

    private void SetNameCityForEdit(String[] NameCityEdit) {
        NameCity = new String[NameCityEdit.length];
        for (int a = 0; a < NameCityEdit.length; a++) {
            NameCity[a] = NameCityEdit[a];
        }
    }

    String[] NameCity = {""};
    String[] NameStatePolArr = {"تهران", "آذربایجان شرقی", "آذربایجان غربی", "اردبیل", "اصفهان", "البرز", "ایلام", "بوشهر", "چهارمحال و بختیاری", "خراسان جنوبی", "خراسان رضوی", "خراسان شمالی", "خوستان", "زنجان", "سمنان", "سیستان و بلوچستان", "فارس", "قزوین", "قم", "کردستان", "کرمان", "کرمانشاه", "کهگلویه و بویراحمد", "گستان", "گیلان", "لرستان", "مازندران", "مرکزی", "هرمزگان", "همدان", "یزد"};

    String[] NameCity_Alarbayejan_Shargi = {"سهند", "کشکسرای", "سیس", "دوزدوزان", "تیمورلو", "صوفیان", "سردرود", "هادیشهر", "هشترود", "زرنق", "ترکمانچای", "ورزقان", "تسوج", "زنوز", "ایلخچی", "شرفخانه", "مهربان", "مبارک شهر", "تیکمه داش", "باسمنج", "سیه رود", "میانه", "خمارلو", "خواجه", "بناب مرند", "قره آغاج", "وایقان", "مراغه", "ممقان", "خامنه", "خسروشاه", "لیلان", "نظرکهریزی", "اهر", "بخشایش", "آقکند", "جوان قلعه", "کلیبر", "مرند", "اسکو", "شندآباد", "شربیان", "گوگان", "بستان آباد", "تبریز", "جلفا", "اچاچی", "هریس", "یامچی", "خاروانا", "کوزه کنان", "خداجو(خراجو)", "آذرشهر", "شبستر", "سراب", "ملکان", "بناب", "هوراند", "کلوانق", "ترک", "عجب شیر", "آبش احمد"};
    String[] NameCity_Alarbayejan_Gharbi = {"تازه شهر", "نالوس", "ایواوغلی", "شاهین دژ", "گردکشانه", "باروق", "سیلوانه", "بازرگان", "نازک علیا", "ربط", "تکاب", "دیزج دیز", "سیمینه", "نوشین", "میاندوآب", "مرگنلر", "سلماس", "آواجیق", "قطور", "محمودآباد", "خوی", "نقده", "سرو", "خلیفان", "پلدشت", "میرآباد", "اشنویه", "زرآباد", "بوکان", "پیرانشهر", "چهاربرج", "قوشچی", "شوط", "ماکو", "سیه چشمه", "سردشت", "کشاورز", "فیرورق", "محمدیار", "ارومیه", "مهاباد", "قره ضیاءالدین"};
    String[] NameCity_Arfebili = {"پارس آباد", "فخراباد", "کلور", "نیر", "اردبیل", "اسلام اباد", "تازه کندانگوت", "مشگین شهر", "جعفرآباد", "نمین", "اصلاندوز", "مرادلو", "خلخال", "کوراییم", "هیر", "گیوی", "گرمی", "لاهرود", "هشتجین", "عنبران", "تازه کند", "قصابه", "رضی", "سرعین", "بیله سوار", "آبی بیگلو"};
    String[] NameCity_Esfahan = {"گزبرخوار", "زیار", "زرین شهر", "گلشن", "پیربکران", "خالدآباد", "سجزی", "گوگد", "تیران", "ونک", "دهق", "زواره", "کاشان", "ابوزیدآباد", "اصغرآباد", "بافران", "شهرضا", "خور", "مجلسی", "هرند", "فولادشهر", "کمشچه", "کلیشادوسودرجان", "لای بید", "قهجاورستان", "چرمهین", "رزوه", "فریدونشهر", "طرق رود", "نصرآباد", "برزک", "سفیدشهر", "سمیرم", "گلدشت", "اردستان", "جوشقان قالی", "بویین ومیاندشت", "کرکوند", "درچه", "انارک", "دولت آباد", "ایمانشهر", "گرگاب", "حسن اباد", "سده لنجان", "حبیب آباد", "بهاران شهر", "میمه", "تودشک", "گلشهر", "رضوانشهر", "داران", "علویجه", "نیک آباد", "مشکات", "آران وبیدگل", "خوانسار", "نجف آباد", "منظریه", "فرخی", "دیزیچه", "اژیه", "زاینده رود", "خورزوق", "قهدریجان", "شاهین شهر", "بهارستان", "چمگردان", "دهاقان", "برف انبار", "بادرود", "کوهپایه", "گلپایگان", "عسگران", "حنا", "کهریزسنگ", "مهاباد", "کامو و چوگان", "افوس", "زیباشهر", "کوشک", "نایین", "سین", "زازران", "مبارکه", "ورزنه", "ورنامخواست", "شاپورآباد", "فلاورجان", "وزوان", "اصفهان", "باغ بهادران", "چادگان", "دامنه", "نطنز", "محمدآباد", "نیاسر", "نوش آباد", "کمه", "جوزدان", "قمصر", "جندق", "طالخونچه", "خمینی شهر", "باغشاد", "دستگرد", "ابریشم"};
    String[] NameCity_Alborz = {"چهارباغ", "آسارا", "کرج", "طالقان", "شهرجدیدهشتگرد", "محمدشهر", "مشکین دشت", "نظرآباد", "هشتگرد", "ماهدشت", "اشتهارد", "کوهسار", "گرمدره", "تنکمان", "گلسار", "کمال شهر", "فردیس"};
    String[] NameCity_Elam = {"آبدانان", "شباب", "موسیان", "بدره", "ایلام", "ایوان", "مهران", "آسمان آباد", "پهله", "مهر", "سراب باغ", "بلاوه", "میمه", "دره شهر", "ارکواز", "مورموری", "توحید", "دهلران", "لومار", "چوار", "زرنه", "صالح آباد", "سرابله", "ماژین", "دلگشا"};
    String[] NameCity_Boshehr = {"ریز", "برازجان", "بندرریگ", "اهرم", "دوراهک", "خورموج", "نخل تقی", "کلمه", "بندردیلم", "وحدتیه", "بنک", "چغادک", "بندردیر", "کاکی", "جم", "دالکی", "بندرگناوه", "آباد", "آبدان", "خارک", "شنبه", "بوشکان", "انارستان", "شبانکاره", "سیراف", "دلوار", "بردستان", "بادوله", "عسلویه", "تنگ ارم", "امام حسن", "سعد آباد", "بندرکنگان", "بوشهر", "بردخون", "آب پخش"};
    String[] NameCity_Tehran = {"تهران", "شاهدشهر", "پیشوا", "جوادآباد", "ارجمند", "ری", "نصیرشهر", "رودهن", "اندیشه", "نسیم شهر", "صباشهر", "ملارد", "شمشک", "پاکدشت", "باقرشهر", "احمد آباد مستوفی", "کیلان", "قرچک", "فردوسیه", "گلستان", "ورامین", "فیروزکوه", "فشم", "پرند", "آبعلی", "چهاردانگه", "بومهن", "وحیدیه", "صفادشت", "لواسان", "فرون اباد", "کهریزک", "رباطکریم", "آبسرد", "باغستان", "صالحیه", "شهریار", "قدس", "تجریش", "شریف آباد", "حسن آباد", "اسلامشهر", "دماوند", "پردیس"};
    String[] NameCity_Hormozgan = {"بیکاء", "تیرور", "گروک", "قشم", "کوشکنار", "کیش", "سرگز", "بندرعباس", "زیارتعلی", "سندرک", "کوهستک", "لمزان", "رویدر", "قلعه قاضی", "فارغان", "ابوموسی", "هشتبندی", "سردشت", "درگهان", "پارسیان", "کنگ", "جناح", "تازیان پایین", "دهبارز", "میناب", "سیریک", "سوزا", "خمیر", "چارک", "حاجی اباد", "فین", "بندرجاسک", "گوهران", "هرمز", "دشتی", "بندرلنگه", "بستک", "تخت"};
    String[] NameCity_ChaharMahalBakhTiari = {"وردنجان", "گوجان", "گهرو", "سورشجان", "سرخون", "شهرکرد", "منج", "بروجن", "پردنجان", "سامان", "فرخ شهر", "صمصامی", "طاقانک", "کاج", "نقنه", "لردگان", "باباحیدر", "دستنا", "سودجان", "بازفت", "هفشجان", "سردشت", "فرادبنه", "چلیچه", "بن", "فارسان", "شلمزار", "نافچ", "دشتک", "بلداجی", "آلونی", "گندمان", "جونقان", "ناغان", "هارونی", "چلگرد", "کیان", "اردل", "سفیددشت", "مال خلیفه"};
    String[] NameCity_Khorasan_Jonobi = {"اسلامیه", "شوسف", "قاین", "عشق آباد", "طبس مسینا", "ارسک", "آیسک", "نیمبلوک", "دیهوک", "سربیشه", "محمدشهر", "بیرجند", "فردوس", "نهبندان", "اسفدن", "گزیک", "حاجی آباد", "سه قلعه", "آرین شهر", "مود", "خوسف", "قهستان", "بشرویه", "سرایان", "خضری دشت بیاض", "طبس", "اسدیه", "زهان"};
    String[] NameCity_Khorasan_Razavi = {"بار", "نیل شهر", "جنگل", "درود", "رباط سنگ", "سلطان آباد", "فریمان", "گناباد", "کاریز", "همت آباد", "سلامی", "باجگیران", "بجستان", "چناران", "درگز", "کلات", "چکنه", "نصرآباد", "بردسکن", "مشهد", "کدکن", "نقاب", "قلندرآباد", "کاشمر", "شاندیز", "نشتیفان", "ششتمد", "شادمهر", "عشق آباد", "چاپشلو", "رشتخوار", "قدمگاه", "صالح آباد", "داورزن", "فرهادگرد", "کاخک", "مشهدریزه", "جغتای", "مزدآوند", "قوچان", "یونسی", "سنگان", "نوخندان", "کندر", "نیشابور", "احمدابادصولت", "شهراباد", "رضویه", "تربت حیدریه", "باخرز", "سفیدسنگ", "بیدخت", "تایباد", "فیروزه", "قاسم آباد", "سبزوار", "فیض آباد", "گلمکان", "لطف آباد", "شهرزو", "خرو", "تربت جام", "انابد", "ملک آباد", "بایک", "دولت آباد", "سرخس", "ریوش", "طرقبه", "خواف", "روداب", "خلیل آباد"};
    String[] NameCity_Kohgiloye_BoirAhmad = {"گراب سفلی", "لنده", "سی سخت", "دهدشت", "یاسوج", "سرفاریاب", "دوگنبدان", "چیتاب", "لیکک", "دیشموک", "مادوان", "باشت", "پاتاوه", "قلعه رییسی", "مارگون", "چرام", "سوق"};
    String[] NameCity_Fars = {"کازرون", "کارزین (فتح آباد)", "فدامی", "خومه زار", "سلطان شهر", "فیروزآباد", "دبیران", "باب انار", "رامجرد", "سروستان", "قره بلاغ", "ارسنجان", "دژکرد", "بیرم", "دهرم", "شیراز", "ایزدخواست", "علامرودشت", "اوز", "وراوی", "بیضا", "نی ریز", "کنارتخته", "امام شهر", "جهرم", "بابامنیر", "گراش", "فسا", "شهرپیر", "حسن اباد", "کامفیروز", "خنج", "خانه زنیان", "استهبان", "بوانات", "لطیفی", "فراشبند", "زرقان", "صغاد", "اشکنان", "قایمیه", "گله دار", "دوبرجی", "آباده طشک", "خرامه", "میمند", "افزر", "دوزه", "سیدان", "کوپن", "زاهدشهر", "قادراباد", "سده", "بنارویه", "سعادت شهر", "شهرصدرا", "سورمق", "حسامی", "جویم", "خوزی", "اردکان", "قطرویه", "نودان", "مبارک آباددیز", "داراب", "نورآباد", "کوار", "نوبندگان", "حاجی آباد", "خاوران", "مرودشت", "کوهنجان", "ششده", "مزایجان", "ایج", "خور", "نوجین", "لپویی", "بهمن", "اهل", "خشت", "مهر", "جنت شهر", "مشکان", "بالاده", "قیر", "قطب آباد", "خانیمن", "مصیری", "میانشهر", "صفاشهر", "اقلید", "عمادده", "مادرسلیمان", "داریان", "رونیز", "کره ای", "لار", "اسیر", "هماشهر", "آباده", "لامرد"};
    String[] NameCity_Khozestan = {"هفتگل", "بیدروبه", "شاوور", "حمزه", "گتوند", "شرافت", "منصوریه", "زهره", "رامهرمز", "بندرامام خمینی", "کوت عبداله", "میداود", "چغامیش", "ملاثانی", "چم گلک", "حر", "شمس آباد", "آبژدان", "چویبده", "مسجدسلیمان", "مقاومت", "ترکالکی", "دارخوین", "سردشت", "لالی", "کوت سیدنعیم", "حمیدیه", "دهدز", "قلعه تل", "میانرود", "رفیع", "اندیمشک", "الوان", "سالند", "صالح شهر", "اروندکنار", "سرداران", "تشان", "رامشیر", "شادگان", "بندرماهشهر", "جایزان", "بستان", "ویس", "اهواز", "فتح المبین", "شهر امام", "قلعه خواجه", "حسینیه", "گلگیر", "مینوشهر", "سماله", "شوشتر", "بهبهان", "هندیجان", "ابوحمیظه", "آغاجاری", "ایذه", "صیدون", "سیاه منصور", "هویزه", "آزادی", "شوش", "دزفول", "جنت مکان", "آبادان", "گوریه", "خرمشهر", "مشراگه", "خنافره", "چمران", "امیدیه", "سوسنگرد", "شیبان", "الهایی", "باغ ملک", "صفی آباد"};
    String[] NameCity_Qom = {"کهک", "قم", "سلفچگان", "جعفریه", "قنوات", "دستجرد"};
    String[] NameCity_Kerman = {"کهنوج", "بلوک", "پاریز", "گنبکی", "زنگی آباد", "بم", "خانوک", "کیانشهر", "جوپار", "عنبرآباد", "جوزم", "نظام شهر", "لاله زار", "کشکوییه", "زیدآباد", "هنزا", "چترود", "جبالبارز", "سیرجان", "رودبار", "کرمان", "بافت", "صفاییه", "منوجان", "اندوهجرد", "هجدک", "خورسند", "امین شهر", "بردسیر", "رفسنجان", "هماشهر", "محمدآباد", "اختیارآباد", "بروات", "ریحان", "کوهبنان", "ماهان", "دوساری", "دهج", "فاریاب", "گلزار", "بهرمان", "بلورد", "فهرج", "کاظم آباد", "جیرفت", "نجف شهر", "قلعه گنج", "باغین", "بزنجان", "زرند", "نودژ", "گلباف", "راور", "خاتون اباد", "نرماشیر", "دشتکار", "مس سرچشمه", "خواجو شهر", "رابر", "راین", "درب بهشت", "یزدان شهر", "زهکلوت", "محی آباد", "مردهک", "شهداد", "ارزوییه", "نگار", "شهربابک", "انار"};
    String[] NameCity_Kordestan = {"قروه", "توپ آغاج", "سروآباد", "بویین سفلی", "زرینه", "دلبران", "سنندج", "یاسوکند", "موچش", "بانه", "مریوان", "سریش آباد", "صاحب", "دهگلان", "بابارشانی", "دیواندره", "برده رشه", "شویشه", "بیجار", "اورامان تخت", "کانی سور", "کانی دینار", "دزج", "سقز", "بلبان آباد", "پیرتاج", "کامیاران", "آرمرده", "چناره"};
    String[] NameCity_KermanShah = {"سنقر", "شاهو", "بانوره", "تازه آباد", "هلشی", "جوانرود", "قصرشیرین", "نوسود", "کرند", "کوزران", "بیستون", "حمیل", "گیلانغرب", "سطر", "روانسر", "پاوه", "ازگله", "کرمانشاه", "میان راهان", "کنگاور", "سرپل ذهاب", "ریجاب", "باینگان", "هرسین", "اسلام آبادغرب", "سرمست", "سومار", "نودشه", "گهواره", "رباط", "صحنه", "گودین"};
    String[] NameCity_Ghazvin = {"سگزآباد", "بیدستان", "کوهین", "رازمیان", "خرمدشت", "آبگرم", "شال", "شریفیه", "اقبالیه", "نرجه", "ارداق", "الوند", "خاکعلی", "سیردان", "ضیاڈآباد", "بویین زهرا", "محمدیه", "محمودآبادنمونه", "معلم کلایه", "اسفرورین", "آوج", "دانسفهان", "آبیک", "قزوین", "تاکستان"};
    String[] NameCity_Sistan = {"محمدی", "شهرک علی اکبر", "بنجار", "گلمورتی", "نگور", "راسک", "بنت", "قصرقند", "جالق", "هیدوچ", "نوک آباد", "زهک", "بمپور", "پیشین", "گشت", "محمدآباد", "زاهدان", "زابلی", "چاه بهار", "زرآباد", "بزمان", "اسپکه", "فنوج", "سراوان", "ادیمی", "زابل", "دوست محمد", "ایرانشهر", "سرباز", "سیرکان", "میرجاوه", "نصرت آباد", "سوران", "خاش", "کنارک", "محمدان", "نیک شهر"};
    String[] NameCity_Semnan = {"ایوانکی", "مجن", "دامغان", "سرخه", "مهدی شهر", "شاهرود", "سمنان", "کهن آباد", "گرمسار", "کلاته خیج", "دیباج", "درجزین", "رودیان", "بسطام", "امیریه", "میامی", "شهمیرزاد", "بیارجمند", "کلاته", "آرادان"};
    String[] NameCity_Khorasan_Shomali = {"چناران شهر", "راز", "پیش قلعه", "قوشخانه", "شوقان", "اسفراین", "گرمه", "قاضی", "شیروان", "حصارگرمخان", "آشخانه", "تیتکانلو", "جاجرم", "بجنورد", "درق", "آوا", "زیارت", "سنخواست", "صفی آباد", "ایور", "فاروج", "لوجلی"};
    String[] NameCity_Zanjan = {"سجاس", "زرین رود", "آب بر", "ارمغانخانه", "کرسف", "هیدج", "سلطانیه", "خرمدره", "نیک پی", "قیدار", "ابهر", "دندی", "حلب", "نوربهار", "گرماب", "چورزق", "زنجان", "سهرورد", "صایین قلعه", "ماه نشان", "زرین آباد"};
    String[] NameCity_Gilan = {"منجیل", "شلمان", "خشکبیجار", "ماکلوان", "سنگر", "مرجقل", "لیسار", "رضوانشهر", "رحیم آباد", "لوندویل", "احمدسرگوراب", "لوشان", "اطاقور", "لشت نشاء", "فومن", "چوبر", "بازار جمعه", "کلاچای", "بندرانزلی", "املش", "رستم آباد", "لاهیجان", "توتکابن", "لنگرود", "کوچصفهان", "صومعه سرا", "اسالم", "دیلمان", "رودسر", "کیاشهر", "شفت", "رودبار", "کومله", "رشت", "ماسوله", "خمام", "ماسال", "واجارگاه", "هشتپر (تالش)", "پره سر", "بره سر", "آستارا", "رودبنه", "جیرنده", "چاف و چمخاله", "لولمان", "گوراب زرمیخ", "حویق", "سیاهکل", "چابکسر", "آستانه اشرفیه", "رانکوه"};
    String[] NameCity_Gholestan = {"سیمین شهر", "مزرعه", "رامیان", "فراغی", "گنبدکاووس", "کردکوی", "مراوه", "بندرترکمن", "نگین شهر", "آق قلا", "سرخنکلاته", "گالیکش", "سنگدوین", "دلند", "بندرگز", "نوده خاندوز", "مینودشت", "گرگان", "گمیش تپه", "علی اباد", "خان ببین", "کلاله", "اینچه برون", "فاضل آباد", "تاتارعلیا", "نوکنده", "آزادشهر", "انبارآلوم", "جلین"};
    String[] NameCity_Lorestan = {"چالانچولان", "بیران شهر", "ویسیان", "شول آباد", "پلدختر", "کوهدشت", "هفت چشمه", "بروجرد", "الشتر", "مومن آباد", "دورود", "زاغه", "چقابل", "الیگودرز", "معمولان", "کوهنانی", "نورآباد", "سپیددشت", "سراب دوره", "ازنا", "گراب", "خرم آباد", "اشترینان", "فیروزآباد", "درب گنبد"};
    String[] NameCity_Mazandaran = {"گلوگاه", "گلوگاه", "پل سفید", "دابودشت", "چالوس", "کیاسر", "بهنمیر", "تنکابن", "کلاردشت", "ایزدشهر", "گتاب", "سلمان شهر", "ارطه", "امیرکلا", "کوهی خیل", "پایین هولار", "گزنک", "محمودآباد", "رامسر", "نوشهر", "خلیل شهر", "کیاکلا", "نور", "مرزیکلا", "فریدونکنار", "زیرآب", "امامزاده عبدالله", "هچیرود", "فریم", "هادی شهر", "نشتارود", "پول", "بهشهر", "کلارآباد", "بلده", "بابل", "جویبار", "آلاشت", "آمل", "نکا", "کتالم وسادات شهر", "بابلسر", "شیرود", "شیرگاه", "رویان", "زرگرمحله", "عباس اباد", "قایم شهر", "خوش رودپی", "مرزن آباد", "ساری", "رینه", "سرخرود", "خرم آباد", "کجور", "رستمکلا", "سورک", "چمستان"};
    String[] NameCity_Markazi = {"آستانه", "خنجین", "نراق", "کمیجان", "آشتیان", "رازقان", "مهاجران", "غرق آباد", "خنداب", "قورچی باشی", "خشکرود", "ساروق", "محلات", "شازند", "ساوه", "میلاجرد", "تفرش", "زاویه", "اراک", "توره", "نوبران", "فرمهین", "دلیجان", "پرندک", "کارچان", "نیمور", "هندودر", "آوه", "جاورسیان", "خمین", "مامونیه", "داودآباد", "شهباز"};
    String[] NameCity_Hamedan = {"زنگنه", "دمق", "سرکان", "آجین", "جورقان", "برزول", "فامنین", "سامن", "بهار", "فرسفج", "شیرین سو", "مریانج", "فیروزان", "قروه درجزین", "ازندریان", "لالجین", "گل تپه", "گیان", "ملایر", "صالح آباد", "تویسرکان", "اسدآباد", "همدان", "نهاوند", "رزن", "جوکار", "مهاجران", "کبودرآهنگ", "قهاوند"};
    String[] NameCity_Yazd = {"مروست", "مهردشت", "حمیدیا", "تفت", "اشکذر", "ندوشن", "یزد", "عقدا", "بهاباد", "ابرکوه", "زارچ", "نیر", "اردکان", "هرات", "بفروییه", "شاهدیه", "بافق", "خضرآباد", "میبد", "مهریز", "احمدآباد"};

}
