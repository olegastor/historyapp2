package by.znaj.rogachev2.historyapptest;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FillWordActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    GridLayout grid;
    TextView te1;

    ArrayList<TextView> tvs;
    ArrayList<String> chars;
    ArrayList<Integer> flags;
    //ArrayList<Integer> checked;
    ArrayList<ArrayList<Integer>> words_nums;
    ArrayList<Integer> checked;
    Button clear;
    Context context;

    int taskId;
    String theme = "";

    String fillword = "";
    int words[][];

    int colorsCounter = 0;
    int[] colorsF = {R.color.colorF1, R.color.colorF2, R.color.colorF3, R.color.colorF4, R.color.colorF5, R.color.colorF6, R.color.colorF7, R.color.colorF8, R.color.colorF9, R.color.colorF10, R.color.colorF11, R.color.colorF12, R.color.colorF13, R.color.colorF14, R.color.colorF15};

    String fillword1 = "ЛЕДКРОМАНЬНЦНАКЕЧНИОЕЕИКННВСКНАЛТОЯООТМЕНАСРОКЛВАЦДТСТРБООМРЕРОЁОЫЛЕОЕКОПЬДРПМНЗСКРЕБОКЯТЦРАВЕНСТВОЫ";
    String fillword2 = "ХЖИВОЗЕМЛЕОМООХОТТАДЗПТТНОАСРЕЯОЫГАВОАЕЛЙДСЕКАДМЙЕСОВСОССУШЦТБООНТТЛИРВЩЖБНЬВЕНАОИДСЕСОТАЛАНБТВЕРПЫО";
    String fillword3 = "МЕТАЛЛКРИЦЖОГРАБРОНАЕЗРУБИТЕЗАЛЕОДИЕРЛЬУГОШАХТЁРЕКОБРЯДНИЕМРРОДИЩЕЛСЕАМВЕКЕДЕЕИШИОГРАЖНННЕФОРУЖИЕИКИ";
    String fillword4 = "ИСКУССТВОЯОТОРГОВЛЯНРГЕРМАНЦЫТНКЕЛЬТЫИКААГРЕКИННЕРМЭТНОСЕЯНЬЕСОПЛЕМВТЫНЗНАТЬЛАЛЦТЭМАЛЬСБАЕИНДОЕВРОПЕ";
    String fillword5 = "ПРСЛАВЯНИСВААНТЕГОЗКЕРОДЫРДВАЛНЕДИНАНИЦАВВОЛЫНЯЧИВЯПОЛОЦКИЯИТМИЧИАКТРНКИКИВОВУАЫОДОРПЛОРДИРАВТЕОТОВМ";
    String fillword6 = "ОПОЛСОРСОВДАНЧДВАБДЕНКЬЕРЕТОВТЯЕИНУЧНРУНЗАНИЖЕИТПИЬРХЕМЫКНОКДИВТСЕЧИЛИВСОКАПИЩЬДЯТИЛИЩЕЕЕОЯЗЫЧЕСТВОЛ";
    String fillword7 = "КИЕВРРОТЬРЛЕТООАДСКЮЯЗИПГННОУРСВСИВОЛОЛИЛАЬОЛЕГДЬКЫБСОЛЕОТТУТЬТНЬТЯСОРРОГНЕДАОМАКНЯЖЕСТВАСМИТРОПОЛИТ";
    String fillword8 = "ДВСЕСССОВОИНАСЛОИКРЛНЕНТАБЗЯОХЕЛИИВОРСВВМЕЕЯНИАЛИОИСАЗАЕВСЩВГОКАНЧИУЕААПВОЛООДЬНМЕНСККМИРИБРЯЧИСЛАВЕ";
    String fillword9 = "НАЛООВТРЕББООСЙНАДАОМЯГАДАДЫВВОРЕРАЗДЗЕАНСИМОСРОМНОИКРУТВБЛИМВОЬСЬОЛЯЯААПЫСЬЛЕНЬХЗОРШАОЬНТНЕГЛЕБСТОС";
    String fillword10 = "ТУРОВЮЧВОЯПЦЯСБРУОЙРОЕРВОИЖКСОСРОЯРЙБОУСАКПТЬБИББЛДОООПАНОИАНВЛПОГАРЙВИЬКОТОРОСИКСИЛОИОНТЧБОРКМКДАВО";
    String fillword11 = "ПГОМЕЙПОНЧОГОРОДНЯЁЕСКУПЕДНОМЧОЖЬЕЦОЕЦАЕБЕРЕГРНЬНРЕРФСАМЕЕКССТВТККОБРИКИАЕЙШИНАНАЛБЕРЕСТЬЕВСЕВОЛОДКО";
    String fillword12 = "КРЕСТОНОСЕВИСИМОСТЬЦАМИССИОНЕРЗНЕРВЛАДИТОИЛЫЦАРЬМЕБЕБИОРЛООЛОСОГОДАГНЬПРИБАЛТИКАНАШЕСТВИЕПАРХИЕПИСКО";
    String fillword13 = "ФЕОДАДЕСЯПБОРТЛАКТТОПТДНИЗУЫИЛОИЕИЗОПСНЮГУНЧМВЫЯАДОНЬЕСТДЦКЬСИГСМЕРЙИЕТЗАЁМЯГОДЫХОЛОПЧЕЛЯДТРЁХПОЛЬЕЬ";
    String fillword14 = "ИЯЗЫЧЕСТВОЕЕФРОСИНИЯРУСАКИРИЛЛМВЕЛМСПАСЕОЕОИЕЙВОАПНРВМФИТОРЯАИДЗОДСВХИСЕЬОДЧЕТИЛТЫРТИАНСЯОХРИСМИТРОП";
    String fillword15 = "УКРЕПТНЯАЗБАРХЛАЖГГБАУБИЕМОРРУШРЕТНММААКНОВЕИОСАМАЯПАКЕТОТОАСЕНТАНФИЯКТЩГУРОСТОЙРИЕСТИЛЬРТАЖЛИЕСОЗАС";
    String fillword16 = "ОТРАНСПОРТДЕЖДАТАНЕЦРМИНСТЛОВУАУПТЧРУМЕШЗЗАИЕЛНЫНКВЫЛИКОТАТАЛКЮДИХОАВАЕАЛЕНБУЛЦАЧЕНИНСУЛИЕДРУЖИЖИЛИЩ";
    String fillword17 = "ПОНЁМКНЯЖЕМАУКАЛОВТСИШЕШНИШАУЛНЙЛТЬТВАЙЯДОКАЕОРДЕНОВТЙТИЯВКЛВГРОЙРОМАНРЬБАНАТПРЯОЖЕМАЙТИУИБВИТЕНЬЯСС";
    String fillword18 = "ВЛОЛЬГЕДИМБАЕКГЕРДНИРСЙСТУТСИААТЬАГРЕСЯНКНУИЯГРЕРДЕВТРАМУЙТАЗАДБРАБНСЦАВЫДДНДЕОЕМОКВИСТАРСБУДИМЕЧЕНО";
    String fillword19 = "ЗАВСОЮЗНИКККИНИЯЯДГАОРСУСТЬВИОНЕИМОЛИРПВФЩОДЕЕВИРЕЛЕЕАИЙВОКОИНФЛНАТАЙЛКИОССТСГИНТЕВЛИЧЕЯЕЕКАТОСОГЛАШ";
    String fillword20 = "ДЕЛЕНИЕОБЩВУСМОНАРХИОДЕВОЛНАИНТЕЛОПОСДЯАЧЛТСОСТЕККИНАЯЛТАЛИОНОЗЕМЬРЕНРАВЛЕНИЕЦТОРАДАНАМЕСЛГОСПОДАРЬЬ";
    String fillword21 = "АРЮВЕЛИРИКШХИНИЦИАЛООИТРОИТКЫНРТСУРАЕАСОНЕКТСЬЛЛТПИКОВТЕЯОЕИБЫХОВИНСЛСРАЗВИТШКЬЬЖИЛЬЁБАСИЗОБОЛЬЦЫКРИ";
    String fillword22 = "ЯПЕРКУНАСКЗНАЯГАЙЛТАЫЧРОДНОООЛРЕСТВОСОЛИЕЛИГИЯТПИКАРУДАМЬОЯИВПСАРИТРРЯОГОТСТВОБДСЭЛИТЕИМОЬЛАВИЕРПОСТ";
    String fillword23 = "НОЕШАЛЛЕНИГКНИЕГВНАЕРВОССООПМЕЕСССТАНОЛСКОРОНААКИТКИЦВИТОИТНОЯАВТСВОРИРОНОЩЕТТОЧВМОГУМИАВЕАССАЛПЕРТС";
    String fillword24 = "ВПОВСТАНЕЦОМАЛЬБОРКХЙНААТАКАИООМАГИСТРВРТПООСАДАГУСНРУНИЧТОЬОЕВАИТЕТЖТВЙТОРЕИНЕСЕТРАЛИТЕТОЩАНИЕОПАСН";
    String fillword25 = "СПРОТИВНИКВВИЛЬКОМИРИДРИГАЙЛОАСПРЕСТОЛРРИЗАГОВОРАИГИЗМУНДКДСРСУДЕБНИАТУГВОЕВОДАОСЕРАМСЕЙМКИНЫСИЯИТАР";
    String fillword26 = "РУГАРТИЛЛЕУСВООХАНСРЧАПОРМОЛТИНРРГУАХДВЯИКУОЖДЯАОКЦОШНЕЖИВРААПЕЯНИРАИЗЙЕНЕИТАТМИЩЕИДИНАСТИИКАРБАЛЕТЯ";
    String fillword27 = "СОПАНЩИВТЕЖСЧИНШНОСИИЛБОЯРАЕННВОВИЕРАВЕАОТНОВЕОЕИВЯЛОГОНВННОХШЦДДСТААЗТЕРЫММЕЩДЬАГОИСПОВЕЛВЕРЗЕМЛЕПО";
    String fillword28 = "САМОМАГИСТЦЕХУАВАНИРОДУПЛЛАВКАКОХРАТУШАТРВЕАРРТСИМЕСНВЛЕНИУРСТВОТОРЕБТТНОСТЬГВОЙМЕСТКОРЧМАОКЧЕХОЗЯИН";
    String fillword29 = "САМОСОЗНАНПАРТНАЕЕГИПЛАОЦРПЛЯЕОИФХЕОИЛИКШТИТРДСОЩУЛВЙАКНКНЕЦИИНМООООЛЫНАЫЫВСППАГКАЛШЬТЬТНРАГЭТНОСАБО";
    String fillword30 = "СТДОСПЕХИСВИЛЕТОПИСУИЛПРОИЗЛЬРТЬКНТОВИТОРАЖИИГЕДЕВЗОДГКИНЕРОФКЧААЕАРАСАОЕСТВОУТТСНТОРИНТЮЬАДРФСМИАРА";

    int[] w1_1 = {0, 1, 2, 12, 22, 23};
    int[] w1_2 = {10, 11, 20, 21, 30, 31, 40, 41, 50, 51, 60, 61};
    int[] w1_3 = {3, 4, 5, 6, 7, 8, 9, 19, 29, 39, 49};
    int[] w1_4 = {13, 14, 24, 32, 33, 34, 42};
    int[] w1_5 = {15, 16, 17, 18, 25, 28, 35, 43, 44, 45, 52, 53, 62};
    int[] w1_6 = {63, 70, 71, 72, 73};
    int[] w1_7 = {80, 81, 82, 83, 84, 85, 86};
    int[] w1_8 = {59, 69, 79, 89, 99};
    int[] w1_9 = {38, 48, 58, 68, 78, 88};
    int[] w1_10 = {54, 64, 74};
    int[] w1_11 = {26, 27, 36, 37, 46, 47, 55, 56, 57, 65, 75};
    int[] w1_12 = {90, 91, 92, 93, 94, 95, 96, 97, 98};
    int[] w1_13 = {66, 67, 76, 77, 87};
    int words1[][] = {w1_1, w1_2, w1_3, w1_4, w1_5, w1_6, w1_7, w1_8, w1_9, w1_10, w1_11, w1_12, w1_13};

    int[] w2_1 = {};
    int[] w2_2 = {};
    int[] w2_3 = {};
    int[] w2_4 = {};
    int[] w2_5 = {};
    int[] w2_6 = {};
    int[] w2_7 = {};
    int[] w2_8 = {};
    int[] w2_9 = {};
    int[] w2_10 = {};
    int[] w2_11 = {};
    int[] w2_12 = {};
    int[] w2_13 = {};
    int[] w2_14 = {};
    int[] w2_15 = {};
    int words2[][] = {w2_1, w2_2, w2_3, w2_4, w2_5, w2_6, w2_7, w2_8, w2_9, w2_10, w2_11, w2_12, w2_13, w2_14, w2_15};

    int[] w3_1 = {};
    int[] w3_2 = {};
    int[] w3_3 = {};
    int[] w3_4 = {};
    int[] w3_5 = {};
    int[] w3_6 = {};
    int[] w3_7 = {};
    int[] w3_8 = {};
    int[] w3_9 = {};
    int[] w3_10 = {};
    int[] w3_11 = {};
    int[] w3_12 = {};
    int[] w3_13 = {};
    int[] w3_14 = {};
    int[] w3_15 = {};
    int words3[][] = {w3_1, w3_2, w3_3, w3_4, w3_5, w3_6, w3_7, w3_8, w3_9, w3_10, w3_11, w3_12, w3_13, w3_14, w3_15};

    int[] w4_1 = {};
    int[] w4_2 = {};
    int[] w4_3 = {};
    int[] w4_4 = {};
    int[] w4_5 = {};
    int[] w4_6 = {};
    int[] w4_7 = {};
    int[] w4_8 = {};
    int[] w4_9 = {};
    int[] w4_10 = {};
    int[] w4_11 = {};
    int[] w4_12 = {};
    int[] w4_13 = {};
    int[] w4_14 = {};
    int[] w4_15 = {};
    int words4[][] = {w4_1, w4_2, w4_3, w4_4, w4_5, w4_6, w4_7, w4_8, w4_9, w4_10, w4_11, w4_12, w4_13, w4_14, w4_15};

    int[] w5_1 = {};
    int[] w5_2 = {};
    int[] w5_3 = {};
    int[] w5_4 = {};
    int[] w5_5 = {};
    int[] w5_6 = {};
    int[] w5_7 = {};
    int[] w5_8 = {};
    int[] w5_9 = {};
    int[] w5_10 = {};
    int[] w5_11 = {};
    int[] w5_12 = {};
    int[] w5_13 = {};
    int[] w5_14 = {};
    int[] w5_15 = {};
    int words5[][] = {w5_1, w5_2, w5_3, w5_4, w5_5, w5_6, w5_7, w5_8, w5_9, w5_10, w5_11, w5_12, w5_13, w5_14, w5_15};

    int[] w6_1 = {};
    int[] w6_2 = {};
    int[] w6_3 = {};
    int[] w6_4 = {};
    int[] w6_5 = {};
    int[] w6_6 = {};
    int[] w6_7 = {};
    int[] w6_8 = {};
    int[] w6_9 = {};
    int[] w6_10 = {};
    int[] w6_11 = {};
    int[] w6_12 = {};
    int[] w6_13 = {};
    int[] w6_14 = {};
    int[] w6_15 = {};
    int words6[][] = {w6_1, w6_2, w6_3, w6_4, w6_5, w6_6, w6_7, w6_8, w6_9, w6_10, w6_11, w6_12, w6_13, w6_14, w6_15};

    int[] w7_1 = {};
    int[] w7_2 = {};
    int[] w7_3 = {};
    int[] w7_4 = {};
    int[] w7_5 = {};
    int[] w7_6 = {};
    int[] w7_7 = {};
    int[] w7_8 = {};
    int[] w7_9 = {};
    int[] w7_10 = {};
    int[] w7_11 = {};
    int[] w7_12 = {};
    int[] w7_13 = {};
    int[] w7_14 = {};
    int[] w7_15 = {};
    int words7[][] = {w7_1, w7_2, w7_3, w7_4, w7_5, w7_6, w7_7, w7_8, w7_9, w7_10, w7_11, w7_12, w7_13, w7_14, w7_15};

    int[] w8_1 = {};
    int[] w8_2 = {};
    int[] w8_3 = {};
    int[] w8_4 = {};
    int[] w8_5 = {};
    int[] w8_6 = {};
    int[] w8_7 = {};
    int[] w8_8 = {};
    int[] w8_9 = {};
    int[] w8_10 = {};
    int[] w8_11 = {};
    int[] w8_12 = {};
    int[] w8_13 = {};
    int[] w8_14 = {};
    int[] w8_15 = {};
    int words8[][] = {w8_1, w8_2, w8_3, w8_4, w8_5, w8_6, w8_7, w8_8, w8_9, w8_10, w8_11, w8_12, w8_13, w8_14, w8_15};

    int[] w9_1 = {};
    int[] w9_2 = {};
    int[] w9_3 = {};
    int[] w9_4 = {};
    int[] w9_5 = {};
    int[] w9_6 = {};
    int[] w9_7 = {};
    int[] w9_8 = {};
    int[] w9_9 = {};
    int[] w9_10 = {};
    int[] w9_11 = {};
    int[] w9_12 = {};
    int[] w9_13 = {};
    int[] w9_14 = {};
    int[] w9_15 = {};
    int words9[][] = {w9_1, w9_2, w9_3, w9_4, w9_5, w9_6, w9_7, w9_8, w9_9, w9_10, w9_11, w9_12, w9_13, w9_14, w9_15};

    int[] w10_1 = {};
    int[] w10_2 = {};
    int[] w10_3 = {};
    int[] w10_4 = {};
    int[] w10_5 = {};
    int[] w10_6 = {};
    int[] w10_7 = {};
    int[] w10_8 = {};
    int[] w10_9 = {};
    int[] w10_10 = {};
    int[] w10_11 = {};
    int[] w10_12 = {};
    int[] w10_13 = {};
    int[] w10_14 = {};
    int[] w10_15 = {};
    int words10[][] = {w10_1, w10_2, w10_3, w10_4, w10_5, w10_6, w10_7, w10_8, w10_9, w10_10, w10_11, w10_12, w10_13, w10_14, w10_15};

    int[] w11_1 = {};
    int[] w11_2 = {};
    int[] w11_3 = {};
    int[] w11_4 = {};
    int[] w11_5 = {};
    int[] w11_6 = {};
    int[] w11_7 = {};
    int[] w11_8 = {};
    int[] w11_9 = {};
    int[] w11_10 = {};
    int[] w11_11 = {};
    int[] w11_12 = {};
    int[] w11_13 = {};
    int[] w11_14 = {};
    int[] w11_15 = {};
    int words11[][] = {w11_1, w11_2, w11_3, w11_4, w11_5, w11_6, w11_7, w11_8, w11_9, w11_10, w11_11, w11_12, w11_13, w11_14, w11_15};

    int[] w12_1 = {};
    int[] w12_2 = {};
    int[] w12_3 = {};
    int[] w12_4 = {};
    int[] w12_5 = {};
    int[] w12_6 = {};
    int[] w12_7 = {};
    int[] w12_8 = {};
    int[] w12_9 = {};
    int[] w12_10 = {};
    int[] w12_11 = {};
    int[] w12_12 = {};
    int[] w12_13 = {};
    int[] w12_14 = {};
    int[] w12_15 = {};
    int words12[][] = {w12_1, w12_2, w12_3, w12_4, w12_5, w12_6, w12_7, w12_8, w12_9, w12_10, w12_11, w12_12, w12_13, w12_14, w12_15};

    int[] w13_1 = {};
    int[] w13_2 = {};
    int[] w13_3 = {};
    int[] w13_4 = {};
    int[] w13_5 = {};
    int[] w13_6 = {};
    int[] w13_7 = {};
    int[] w13_8 = {};
    int[] w13_9 = {};
    int[] w13_10 = {};
    int[] w13_11 = {};
    int[] w13_12 = {};
    int[] w13_13 = {};
    int[] w13_14 = {};
    int[] w13_15 = {};
    int words13[][] = {w13_1, w13_2, w13_3, w13_4, w13_5, w13_6, w13_7, w13_8, w13_9, w13_10, w13_11, w13_12, w13_13, w13_14, w13_15};

    int[] w14_1 = {};
    int[] w14_2 = {};
    int[] w14_3 = {};
    int[] w14_4 = {};
    int[] w14_5 = {};
    int[] w14_6 = {};
    int[] w14_7 = {};
    int[] w14_8 = {};
    int[] w14_9 = {};
    int[] w14_10 = {};
    int[] w14_11 = {};
    int[] w14_12 = {};
    int[] w14_13 = {};
    int[] w14_14 = {};
    int[] w14_15 = {};
    int words14[][] = {w14_1, w14_2, w14_3, w14_4, w14_5, w14_6, w14_7, w14_8, w14_9, w14_10, w14_11, w14_12, w14_13, w14_14, w14_15};

    int[] w15_1 = {};
    int[] w15_2 = {};
    int[] w15_3 = {};
    int[] w15_4 = {};
    int[] w15_5 = {};
    int[] w15_6 = {};
    int[] w15_7 = {};
    int[] w15_8 = {};
    int[] w15_9 = {};
    int[] w15_10 = {};
    int[] w15_11 = {};
    int[] w15_12 = {};
    int[] w15_13 = {};
    int[] w15_14 = {};
    int[] w15_15 = {};
    int words15[][] = {w15_1, w15_2, w15_3, w15_4, w15_5, w15_6, w15_7, w15_8, w15_9, w15_10, w15_11, w15_12, w15_13, w15_14, w15_15};

    int[] w16_1 = {};
    int[] w16_2 = {};
    int[] w16_3 = {};
    int[] w16_4 = {};
    int[] w16_5 = {};
    int[] w16_6 = {};
    int[] w16_7 = {};
    int[] w16_8 = {};
    int[] w16_9 = {};
    int[] w16_10 = {};
    int[] w16_11 = {};
    int[] w16_12 = {};
    int[] w16_13 = {};
    int[] w16_14 = {};
    int[] w16_15 = {};
    int words16[][] = {w16_1, w16_2, w16_3, w16_4, w16_5, w16_6, w16_7, w16_8, w16_9, w16_10, w16_11, w16_12, w16_13, w16_14, w16_15};

    int[] w17_1 = {};
    int[] w17_2 = {};
    int[] w17_3 = {};
    int[] w17_4 = {};
    int[] w17_5 = {};
    int[] w17_6 = {};
    int[] w17_7 = {};
    int[] w17_8 = {};
    int[] w17_9 = {};
    int[] w17_10 = {};
    int[] w17_11 = {};
    int[] w17_12 = {};
    int[] w17_13 = {};
    int[] w17_14 = {};
    int[] w17_15 = {};
    int words17[][] = {w17_1, w17_2, w17_3, w17_4, w17_5, w17_6, w17_7, w17_8, w17_9, w17_10, w17_11, w17_12, w17_13, w17_14, w17_15};

    int[] w18_1 = {};
    int[] w18_2 = {};
    int[] w18_3 = {};
    int[] w18_4 = {};
    int[] w18_5 = {};
    int[] w18_6 = {};
    int[] w18_7 = {};
    int[] w18_8 = {};
    int[] w18_9 = {};
    int[] w18_10 = {};
    int[] w18_11 = {};
    int[] w18_12 = {};
    int[] w18_13 = {};
    int[] w18_14 = {};
    int[] w18_15 = {};
    int words18[][] = {w18_1, w18_2, w18_3, w18_4, w18_5, w18_6, w18_7, w18_8, w18_9, w18_10, w18_11, w18_12, w18_13, w18_14, w18_15};

    int[] w19_1 = {};
    int[] w19_2 = {};
    int[] w19_3 = {};
    int[] w19_4 = {};
    int[] w19_5 = {};
    int[] w19_6 = {};
    int[] w19_7 = {};
    int[] w19_8 = {};
    int[] w19_9 = {};
    int[] w19_10 = {};
    int[] w19_11 = {};
    int[] w19_12 = {};
    int[] w19_13 = {};
    int[] w19_14 = {};
    int[] w19_15 = {};
    int words19[][] = {w19_1, w19_2, w19_3, w19_4, w19_5, w19_6, w19_7, w19_8, w19_9, w19_10, w19_11, w19_12, w19_13, w19_14, w19_15};

    int[] w20_1 = {};
    int[] w20_2 = {};
    int[] w20_3 = {};
    int[] w20_4 = {};
    int[] w20_5 = {};
    int[] w20_6 = {};
    int[] w20_7 = {};
    int[] w20_8 = {};
    int[] w20_9 = {};
    int[] w20_10 = {};
    int[] w20_11 = {};
    int[] w20_12 = {};
    int[] w20_13 = {};
    int[] w20_14 = {};
    int[] w20_15 = {};
    int words20[][] = {w20_1, w20_2, w20_3, w20_4, w20_5, w20_6, w20_7, w20_8, w20_9, w20_10, w20_11, w20_12, w20_13, w20_14, w20_15};

    int[] w21_1 = {};
    int[] w21_2 = {};
    int[] w21_3 = {};
    int[] w21_4 = {};
    int[] w21_5 = {};
    int[] w21_6 = {};
    int[] w21_7 = {};
    int[] w21_8 = {};
    int[] w21_9 = {};
    int[] w21_10 = {};
    int[] w21_11 = {};
    int[] w21_12 = {};
    int[] w21_13 = {};
    int[] w21_14 = {};
    int[] w21_15 = {};
    int words21[][] = {w21_1, w21_2, w21_3, w21_4, w21_5, w21_6, w21_7, w21_8, w21_9, w21_10, w21_11, w21_12, w21_13, w21_14, w21_15};

    int[] w22_1 = {};
    int[] w22_2 = {};
    int[] w22_3 = {};
    int[] w22_4 = {};
    int[] w22_5 = {};
    int[] w22_6 = {};
    int[] w22_7 = {};
    int[] w22_8 = {};
    int[] w22_9 = {};
    int[] w22_10 = {};
    int[] w22_11 = {};
    int[] w22_12 = {};
    int[] w22_13 = {};
    int[] w22_14 = {};
    int[] w22_15 = {};
    int words22[][] = {w22_1, w22_2, w22_3, w22_4, w22_5, w22_6, w22_7, w22_8, w22_9, w22_10, w22_11, w22_12, w22_13, w22_14, w22_15};

    int[] w23_1 = {};
    int[] w23_2 = {};
    int[] w23_3 = {};
    int[] w23_4 = {};
    int[] w23_5 = {};
    int[] w23_6 = {};
    int[] w23_7 = {};
    int[] w23_8 = {};
    int[] w23_9 = {};
    int[] w23_10 = {};
    int[] w23_11 = {};
    int[] w23_12 = {};
    int[] w23_13 = {};
    int[] w23_14 = {};
    int[] w23_15 = {};
    int words23[][] = {w23_1, w23_2, w23_3, w23_4, w23_5, w23_6, w23_7, w23_8, w23_9, w23_10, w23_11, w23_12, w23_13, w23_14, w23_15};

    int[] w24_1 = {};
    int[] w24_2 = {};
    int[] w24_3 = {};
    int[] w24_4 = {};
    int[] w24_5 = {};
    int[] w24_6 = {};
    int[] w24_7 = {};
    int[] w24_8 = {};
    int[] w24_9 = {};
    int[] w24_10 = {};
    int[] w24_11 = {};
    int[] w24_12 = {};
    int[] w24_13 = {};
    int[] w24_14 = {};
    int[] w24_15 = {};
    int words24[][] = {w24_1, w24_2, w24_3, w24_4, w24_5, w24_6, w24_7, w24_8, w24_9, w24_10, w24_11, w24_12, w24_13, w24_14, w24_15};

    int[] w25_1 = {};
    int[] w25_2 = {};
    int[] w25_3 = {};
    int[] w25_4 = {};
    int[] w25_5 = {};
    int[] w25_6 = {};
    int[] w25_7 = {};
    int[] w25_8 = {};
    int[] w25_9 = {};
    int[] w25_10 = {};
    int[] w25_11 = {};
    int[] w25_12 = {};
    int[] w25_13 = {};
    int[] w25_14 = {};
    int[] w25_15 = {};
    int words25[][] = {w25_1, w25_2, w25_3, w25_4, w25_5, w25_6, w25_7, w25_8, w25_9, w25_10, w25_11, w25_12, w25_13, w25_14, w25_15};

    int[] w26_1 = {};
    int[] w26_2 = {};
    int[] w26_3 = {};
    int[] w26_4 = {};
    int[] w26_5 = {};
    int[] w26_6 = {};
    int[] w26_7 = {};
    int[] w26_8 = {};
    int[] w26_9 = {};
    int[] w26_10 = {};
    int[] w26_11 = {};
    int[] w26_12 = {};
    int[] w26_13 = {};
    int[] w26_14 = {};
    int[] w26_15 = {};
    int words26[][] = {w26_1, w26_2, w26_3, w26_4, w26_5, w26_6, w26_7, w26_8, w26_9, w26_10, w26_11, w26_12, w26_13, w26_14, w26_15};

    int[] w27_1 = {};
    int[] w27_2 = {};
    int[] w27_3 = {};
    int[] w27_4 = {};
    int[] w27_5 = {};
    int[] w27_6 = {};
    int[] w27_7 = {};
    int[] w27_8 = {};
    int[] w27_9 = {};
    int[] w27_10 = {};
    int[] w27_11 = {};
    int[] w27_12 = {};
    int[] w27_13 = {};
    int[] w27_14 = {};
    int[] w27_15 = {};
    int words27[][] = {w27_1, w27_2, w27_3, w27_4, w27_5, w27_6, w27_7, w27_8, w27_9, w27_10, w27_11, w27_12, w27_13, w27_14, w27_15};

    int[] w28_1 = {};
    int[] w28_2 = {};
    int[] w28_3 = {};
    int[] w28_4 = {};
    int[] w28_5 = {};
    int[] w28_6 = {};
    int[] w28_7 = {};
    int[] w28_8 = {};
    int[] w28_9 = {};
    int[] w28_10 = {};
    int[] w28_11 = {};
    int[] w28_12 = {};
    int[] w28_13 = {};
    int[] w28_14 = {};
    int[] w28_15 = {};
    int words28[][] = {w28_1, w28_2, w28_3, w28_4, w28_5, w28_6, w28_7, w28_8, w28_9, w28_10, w28_11, w28_12, w28_13, w28_14, w28_15};

    int[] w29_1 = {};
    int[] w29_2 = {};
    int[] w29_3 = {};
    int[] w29_4 = {};
    int[] w29_5 = {};
    int[] w29_6 = {};
    int[] w29_7 = {};
    int[] w29_8 = {};
    int[] w29_9 = {};
    int[] w29_10 = {};
    int[] w29_11 = {};
    int[] w29_12 = {};
    int[] w29_13 = {};
    int[] w29_14 = {};
    int[] w29_15 = {};
    int words29[][] = {w29_1, w29_2, w29_3, w29_4, w29_5, w29_6, w29_7, w29_8, w29_9, w29_10, w29_11, w29_12, w29_13, w29_14, w29_15};

    int[] w30_1 = {};
    int[] w30_2 = {};
    int[] w30_3 = {};
    int[] w30_4 = {};
    int[] w30_5 = {};
    int[] w30_6 = {};
    int[] w30_7 = {};
    int[] w30_8 = {};
    int[] w30_9 = {};
    int[] w30_10 = {};
    int[] w30_11 = {};
    int[] w30_12 = {};
    int[] w30_13 = {};
    int[] w30_14 = {};
    int[] w30_15 = {};
    int words30[][] = {w30_1, w30_2, w30_3, w30_4, w30_5, w30_6, w30_7, w30_8, w30_9, w30_10, w30_11, w30_12, w30_13, w30_14, w30_15};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_word);

        context = getApplicationContext();

        getSupportActionBar().setTitle(R.string.app_name_short);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_actbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            taskId = extras.getInt("id");
            theme = extras.getString("theme");
        }

        clear = findViewById(R.id.clear);

        te1 = findViewById(R.id.te1);
        te1.setText(theme);

        grid = findViewById(R.id.grid1);
        //grid.setOnTouchListener(this);


        chars = new ArrayList<>();
        tvs = new ArrayList<>();

        flags = new ArrayList<>();
        checked = new ArrayList<>();
        flags.clear();
        checked.clear();

        for (int i = 0; i < 100; i++) {
            flags.add(0);
        }

        tvs.clear();
        tvs.add((TextView) findViewById(R.id.tv0));
        tvs.add((TextView) findViewById(R.id.tv1));
        tvs.add((TextView) findViewById(R.id.tv2));
        tvs.add((TextView) findViewById(R.id.tv3));
        tvs.add((TextView) findViewById(R.id.tv4));
        tvs.add((TextView) findViewById(R.id.tv5));
        tvs.add((TextView) findViewById(R.id.tv6));
        tvs.add((TextView) findViewById(R.id.tv7));
        tvs.add((TextView) findViewById(R.id.tv8));
        tvs.add((TextView) findViewById(R.id.tv9));
        tvs.add((TextView) findViewById(R.id.tv10));
        tvs.add((TextView) findViewById(R.id.tv11));
        tvs.add((TextView) findViewById(R.id.tv12));
        tvs.add((TextView) findViewById(R.id.tv13));
        tvs.add((TextView) findViewById(R.id.tv14));
        tvs.add((TextView) findViewById(R.id.tv15));
        tvs.add((TextView) findViewById(R.id.tv16));
        tvs.add((TextView) findViewById(R.id.tv17));
        tvs.add((TextView) findViewById(R.id.tv18));
        tvs.add((TextView) findViewById(R.id.tv19));
        tvs.add((TextView) findViewById(R.id.tv20));
        tvs.add((TextView) findViewById(R.id.tv21));
        tvs.add((TextView) findViewById(R.id.tv22));
        tvs.add((TextView) findViewById(R.id.tv23));
        tvs.add((TextView) findViewById(R.id.tv24));
        tvs.add((TextView) findViewById(R.id.tv25));
        tvs.add((TextView) findViewById(R.id.tv26));
        tvs.add((TextView) findViewById(R.id.tv27));
        tvs.add((TextView) findViewById(R.id.tv28));
        tvs.add((TextView) findViewById(R.id.tv29));
        tvs.add((TextView) findViewById(R.id.tv30));
        tvs.add((TextView) findViewById(R.id.tv31));
        tvs.add((TextView) findViewById(R.id.tv32));
        tvs.add((TextView) findViewById(R.id.tv33));
        tvs.add((TextView) findViewById(R.id.tv34));
        tvs.add((TextView) findViewById(R.id.tv35));
        tvs.add((TextView) findViewById(R.id.tv36));
        tvs.add((TextView) findViewById(R.id.tv37));
        tvs.add((TextView) findViewById(R.id.tv38));
        tvs.add((TextView) findViewById(R.id.tv39));
        tvs.add((TextView) findViewById(R.id.tv40));
        tvs.add((TextView) findViewById(R.id.tv41));
        tvs.add((TextView) findViewById(R.id.tv42));
        tvs.add((TextView) findViewById(R.id.tv43));
        tvs.add((TextView) findViewById(R.id.tv44));
        tvs.add((TextView) findViewById(R.id.tv45));
        tvs.add((TextView) findViewById(R.id.tv46));
        tvs.add((TextView) findViewById(R.id.tv47));
        tvs.add((TextView) findViewById(R.id.tv48));
        tvs.add((TextView) findViewById(R.id.tv49));
        tvs.add((TextView) findViewById(R.id.tv50));
        tvs.add((TextView) findViewById(R.id.tv51));
        tvs.add((TextView) findViewById(R.id.tv52));
        tvs.add((TextView) findViewById(R.id.tv53));
        tvs.add((TextView) findViewById(R.id.tv54));
        tvs.add((TextView) findViewById(R.id.tv55));
        tvs.add((TextView) findViewById(R.id.tv56));
        tvs.add((TextView) findViewById(R.id.tv57));
        tvs.add((TextView) findViewById(R.id.tv58));
        tvs.add((TextView) findViewById(R.id.tv59));
        tvs.add((TextView) findViewById(R.id.tv60));
        tvs.add((TextView) findViewById(R.id.tv61));
        tvs.add((TextView) findViewById(R.id.tv62));
        tvs.add((TextView) findViewById(R.id.tv63));
        tvs.add((TextView) findViewById(R.id.tv64));
        tvs.add((TextView) findViewById(R.id.tv65));
        tvs.add((TextView) findViewById(R.id.tv66));
        tvs.add((TextView) findViewById(R.id.tv67));
        tvs.add((TextView) findViewById(R.id.tv68));
        tvs.add((TextView) findViewById(R.id.tv69));
        tvs.add((TextView) findViewById(R.id.tv70));
        tvs.add((TextView) findViewById(R.id.tv71));
        tvs.add((TextView) findViewById(R.id.tv72));
        tvs.add((TextView) findViewById(R.id.tv73));
        tvs.add((TextView) findViewById(R.id.tv74));
        tvs.add((TextView) findViewById(R.id.tv75));
        tvs.add((TextView) findViewById(R.id.tv76));
        tvs.add((TextView) findViewById(R.id.tv77));
        tvs.add((TextView) findViewById(R.id.tv78));
        tvs.add((TextView) findViewById(R.id.tv79));
        tvs.add((TextView) findViewById(R.id.tv80));
        tvs.add((TextView) findViewById(R.id.tv81));
        tvs.add((TextView) findViewById(R.id.tv82));
        tvs.add((TextView) findViewById(R.id.tv83));
        tvs.add((TextView) findViewById(R.id.tv84));
        tvs.add((TextView) findViewById(R.id.tv85));
        tvs.add((TextView) findViewById(R.id.tv86));
        tvs.add((TextView) findViewById(R.id.tv87));
        tvs.add((TextView) findViewById(R.id.tv88));
        tvs.add((TextView) findViewById(R.id.tv89));
        tvs.add((TextView) findViewById(R.id.tv90));
        tvs.add((TextView) findViewById(R.id.tv91));
        tvs.add((TextView) findViewById(R.id.tv92));
        tvs.add((TextView) findViewById(R.id.tv93));
        tvs.add((TextView) findViewById(R.id.tv94));
        tvs.add((TextView) findViewById(R.id.tv95));
        tvs.add((TextView) findViewById(R.id.tv96));
        tvs.add((TextView) findViewById(R.id.tv97));
        tvs.add((TextView) findViewById(R.id.tv98));
        tvs.add((TextView) findViewById(R.id.tv99));

        switch (taskId) {
            case 0: {
                fillword = fillword1;
                words = words1;
                break;
            }
            case 1: {
                fillword = fillword2;
                words = words1;
                break;
            }
            case 2: {
                fillword = fillword3;
                words = words3;
                break;
            }
            case 3: {
                fillword = fillword4;
                words = words4;
                break;
            }
            case 4: {
                fillword = fillword5;
                words = words5;
                break;
            }
            case 5: {
                fillword = fillword6;
                words = words6;
                break;
            }
            case 6: {
                fillword = fillword7;
                words = words7;
                break;
            }
            case 7: {
                fillword = fillword8;
                words = words8;
                break;
            }
            case 8: {
                fillword = fillword9;
                words = words9;
                break;
            }
            case 9: {
                fillword = fillword10;
                words = words10;
                break;
            }
            case 10: {
                fillword = fillword11;
                words = words11;
                break;
            }
            case 11: {
                fillword = fillword12;
                words = words12;
                break;
            }
            case 12: {
                fillword = fillword13;
                words = words13;
                break;
            }
            case 13: {
                fillword = fillword14;
                words = words14;
                break;
            }
            case 14: {
                fillword = fillword15;
                words = words15;
                break;
            }
            case 15: {
                fillword = fillword16;
                words = words16;
                break;
            }
            case 16: {
                fillword = fillword17;
                words = words17;
                break;
            }
            case 17: {
                fillword = fillword18;
                words = words18;
                break;
            }
            case 18: {
                fillword = fillword19;
                words = words19;
                break;
            }
            case 19: {
                fillword = fillword20;
                words = words20;
                break;
            }
            case 20: {
                fillword = fillword21;
                words = words21;
                break;
            }
            case 21: {
                fillword = fillword22;
                words = words22;
                break;
            }
            case 22: {
                fillword = fillword23;
                words = words23;
                break;
            }
            case 23: {
                fillword = fillword24;
                words = words24;
                break;
            }
            case 24: {
                fillword = fillword25;
                words = words25;
                break;
            }
            case 25: {
                fillword = fillword26;
                words = words26;
                break;
            }
            case 26: {
                fillword = fillword27;
                words = words27;
                break;
            }
            case 27: {
                fillword = fillword28;
                words = words28;
                break;
            }
            case 28: {
                fillword = fillword29;
                words = words29;
                break;
            }
            case 29: {
                fillword = fillword30;
                words = words30;
                break;
            }
        }


        char[] strToArray = fillword.toCharArray();
        for (int i = 0; i < strToArray.length; i++) {
            chars.add(String.valueOf(strToArray[i]));
            tvs.get(i).setText(chars.get(i));
            tvs.get(i).setOnTouchListener(this);
        }


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checked.clear();
                for (int i = 0; i < flags.size(); i++) {
                    if (flags.get(i) == 0) {
                        tvs.get(i).setBackgroundColor(ContextCompat.getColor(context, R.color.colorBack));
                    }
                }
            }
        });


    }

    /*private void checkedClear(){
        for (int i = 0;  i< checked.size(); i++){
            checked.set(i, 0);
        }

    }*/

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //ArrayList<Integer> tr = new ArrayList<>();
        if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {
            view.setBackgroundColor(getResources().getColor(R.color.colorGrey));
            //view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGrey));
            if (!checked.contains(tvs.indexOf(view))) {
                checked.add(tvs.indexOf(view));
            }

            Collections.sort(checked, new Comparator<Integer>() {
                public int compare(Integer o1, Integer o2) {
                    return o1.compareTo(o2);
                }
            });

            te1.setText(checked.toString());

            for (int j = 0; j < words.length; j++) {
                if (checked.equals(getArrayList(words[j]))) {
                    //Toast.makeText(getApplicationContext(), "slovo", Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < checked.size(); i++) {
                        tvs.get(checked.get(i)).setBackgroundColor(getResources().getColor(colorsF[colorsCounter]));
                        tvs.get(checked.get(i)).setOnTouchListener(null);
                        flags.set(checked.get(i), 1);
                    }
                    checked.clear();
                    colorsCounter++;
                }
            }

        }

        /*if (motionEvent.getAction() == MotionEvent.ACTION_MOVE){
            Toast.makeText(this, "move", Toast.LENGTH_SHORT).show();
        }
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            for (int i = 0; i < 100; i++) {
                temp = (Button) grid.getChildAt(i);
                temp.setBackgroundColor(getResources().getColor(R.color.colorHighlight));
                Toast.makeText(this, "up", Toast.LENGTH_SHORT).show();
            }
        }*/

        return false;
    }

    private ArrayList<Integer> getArrayList(int[] a1) {
        ArrayList<Integer> array = new ArrayList<>();
        array.clear();
        for (int i = 0; i < a1.length; i++) {
            array.add(a1[i]);
        }
        return array;
    }
}
