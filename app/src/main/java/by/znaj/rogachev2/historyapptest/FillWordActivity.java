package by.znaj.rogachev2.historyapptest;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FillWordActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    //GridLayout grid;
    TextView te1;

    ArrayList<TextView> tvs;
    ArrayList<String> chars;
    ArrayList<Integer> flags;
    //ArrayList<Integer> checked;
    ArrayList<Integer> checked;
    Button clear;
    Context context;

    int taskId;
    String theme = "";

    String fillword = "";
    int[][] words;

    int colorsCounter = 0;
    int[] colorsF = {R.color.colorF1, R.color.colorF2, R.color.colorF3, R.color.colorF4, R.color.colorF5, R.color.colorF6, R.color.colorF7, R.color.colorF8, R.color.colorF9, R.color.colorF10, R.color.colorF11, R.color.colorF12, R.color.colorF13, R.color.colorF14, R.color.colorF15};

    String fillword1 = "ЛЕДКРОМАНЬНЦНАКЕЧНИОЕЕИКННВСКНАЛТОЯООТМЕНАСРОКЛВАЦДТСТРБООМРЕРОЁОЫЛЕОЕКОПЬДРПМНЗСКРЕБОКЯТЦРАВЕНСТВОЫ";
    String fillword2 = "ХЖИВОЗЕМЛЕОМООХОТТАДЗПТТНОАСРЕЯОЫГАВОАЕЛЙДСЕКАДМЙЕСОВСОССУШЦТБООНТТЛИРВЩЖБНЬВЕНАОИДСЕСОТАЛАНЬТВЕРПЫО";
    String fillword3 = "МЕТАЛЛКРИЦЖОГРАБРОНАЕЗРУБИТЕЗАЛЕОДИЕРЛЬУГОШАХТЁРЕКОБРЯДНИЕМРРОДИЩЕЛСЕАМВЕКЕДЕЕИШИОГРАЖНННЕФОРУЖИЕИКИ";
    String fillword4 = "ИСКУССТВОЯОТОРГОВЛЯНРГЕРМАНЦЫТНКЕЛЬТЫИКААГРЕКИННЕРМЭТНОСЕЯНЬЕСОПЛЕМВТЫНЗНАТЬЛАЛЦТЭМАЛЬСБАЕИНДОЕВРОПЕ";
    String fillword5 = "ВПСЛАВЯНИСЕРАНТЕГОЗКНАРОЫРДВАЛЕДЫДИНАИЦАВВОЛЫНЬЧИВЯПОЛОЦКИЯИТМИЧИАКТРНКИКИВОВУАЫОДОРПЛОРДИРАВТЕОТОВМ";
    String fillword6 = "ОПОЛСОРСОВДАНЧДВАБДЕНКЬЕРЕТОВТЯЕИНУЧНРУНЗАНИЖЕИТПИЬРХЕМЫКНОКДИВТСЕЧИЛИВСОКАПИЩЬДЯТИЛИЩЕЕЕОЯЗЫЧЕСТВОЛ";
    String fillword7 = "КИЕВРРОТЬРЛЕТООАДСКЮЯЗИПГННОУРСВСИВОЛОЛИЛАЬОЛЕГДЬКЫБСОЛЕОТТУТЬТНЬТЯСОРРОГНЕДАОМАКНЯЖЕСТВАСМИТРОПОЛИТ";
    String fillword8 = "ДВСЕСССОВОИНАСЛОИКРЛНЕНТАБЗЯОХЕЛИИВОРСВВМЕЕЯНИАЛИОИСАЗАЕВСЩВГОКАНЧИУЕААПВОЛООДЬНМЕНСККМИРИБРЯЧИСЛАВЕ";
    String fillword9 = "НАЛООВТРЕББООСЙНАДАОМЯГАДАДЫВВОРЕРАЗДЗЕАНСИМОСРОМНОИКРУТВБЛИМВОЬСЬОЛЯЯААПЫСЬЛЕНЬХЗОРШАОЬНТНЕГЛЕБСТОС";
    String fillword10 = "ТУРОВЮЧВОЯПЦЯСБРУОЙРОЕРВОИЖКСОСРОЯРЙБООСАКПТЬБИБКЛДОООПАНОААНВЛПОГАРЯВИЬКОТОРОНИКСИЛОИОННЧБОРКМКДАЫЙ";
    String fillword11 = "ПГОМЕЙПОНЧОГОРОДНЯЁЕСКУПЕДНОМЧОЖЬЕЦОЕЦАЕБЕРЕГРНЬНРЕРФСАМЕЕКССТВТККОБРИКИАЕЙЩИНАНАЛБЕРЕСТЬЕВСЕВОЛОДКО";
    String fillword12 = "КРЕСТОНОСЕВИСИМОСТЬЦАМИССИОНЕРЗНЕРВЛАДИМОИЛЫЦАРНГИБЕБИОРЬООРОСОГОДАМЛЫПРИБАЛТИКАНАШЕСТВИЕПАРХИЕПИСКО";
    String fillword13 = "ФЕОДАДЕСЯПБОРТЛАКТТОПТДНИЗУЫИЛОИЕИЗОПСНЮГУНЧМВЫЯАДОНЬЕСТДЦКЬСИГСМЕРЙИЕТЗАЁМЯГОДЫХОЛОПЧЕЛЯДТРЁХПОЛЬЕЬ";
    String fillword14 = "ИЯЗЫЧЕСТВОЕЕВФРОСИНИРУСАКИРИЛЯМВЕЛМДУХЛЕОЕОИЕЙВОАПНРВМФИТОРЯАИДЗОДСВХИСЕЬОДЧЕТИЛТЫРТИАНСЯОХРИСМИТРОП";
    String fillword15 = "УКРЕПТНЯАЗБАРХЛАЖГГБАУБИЕМОРРУШРЕТНОМААКНОВЕИТСАМАЯПАКЕНОТОЗКУНТАОФИЯОАЛГУРСТЬХДРЬЕСТИЛАРЧУТЛИЕСОМЙИ";
    String fillword16 = "ОТРАНСПОРТДЕЖДАТАНЕЦРМИНСТЛОВУАУПТЧРУМЕШЗЗАИЕЛНЫНКВЫЛИКОТАТАЛКЮДИХОАВАЕАЛЕНБУЛЦАЧЕНИНСУЛИЕДРУЖИЖИЛИЩ";
    String fillword17 = "ПОНЁМКНЯЖЕМАУКАЛОВТСИШЕШНИШАУЛНЙЛТЬТВАЙЯДОКАЕОРДЕНОВТЙТИЯВКЛВГРОЙРОМАНРЬБАНАТПРЯОЖЕМАЙТИУИБВИТЕНЬЯСС";
    String fillword18 = "ВЛОЛЬГЕДИМБАЕКГЕРДНИРСЙСТУТСИААТЬАГРЕСЯНКНУИЯГРЕРДЕВТРАМУЙТАЗАДБРАБНСЦАВЫДДНДЕОЕМОКВИСТАРСБУДИМЕЧЕНО";
    String fillword19 = "ЗАВСОЮЗНИКККИНИЯЯДГАОРСУСТЬВИОНЕИМОЛИРПВФЩОДЕЕВИРЕЛЕЕАИЙВОКОИНФЛНАТАЙЛКИОССТСГИНТЕВЛИЧЕЯЕЕКАТОСОГЛАШ";
    String fillword20 = "ДЕЛЕНИЕОБЩВУСМОНАРХИОДЕВОЛНАИНТЕЛОПОСДЯАЧЛТСОСТЕККИНАЯЛТАЛИОНОЗЕМЬРЕНРАВЛЕНИЕЦТОРАДАНАМЕСЛГОСПОДАРЬЬ";
    String fillword21 = "АРЮВЕЛИРИКШХИНИЦИАЛООИТРОИТКЫНРТСУРАЕАСОНЕКТСЬЛВТПИКОВТЕЯОЕИБЫХОВИНСЛСРАЗВИТШКЬЬЖИЛЬЁБАСИЗОБОЛЬЦЫКРИ";
    String fillword22 = "ЯПЕРКУНАСКЗНАЯГАЙЛТАЫЧРОДНОООЛРЕСТВОСОЛИЕЛИГИЯТПИКАРУДАМЬОЯИВПСАРИТРТСОГОТСТВОЬОСЭЛИТЕРПИМЛАВИЕОБРЯД";
    String fillword23 = "НОЕШАЛЛЕНИГКНИЕГВНАЕРВОССООПМЕЕСССТАНОЛСКОРОНААКИТКИЦВИТОИТНОЯАВТСВОРИРОНОЩЕТТОЧВМОГУМИАВЕАССАЛПЕРТС";
    String fillword24 = "ВПОВСТАНЕЦОМАЛЬБОРКХЙНААТАКАИООМАГИСТРВРТПООСАДАГУСНРУНИЧТОЬОЕВАИТЕТЖТВЙТОРЕИНЕСЕТРАЛИТЕТОЩАНИЕОПАСН";
    String fillword25 = "СПРОТИВНИКВВИЛЬКОМИРИДРИГАЙЛОАСПРЕСТОЛРРИЗАГОВОРАИГИЗМУНДКДСРСУДЕБНИАТУГВОЕВОДАОСЕРАМСЕЙМКИНЫСИЯИТАР";
    String fillword26 = "РУГАРТИЛЛЕУСВООХАНСРЧАПОРМОЛТИНРРГУИГДВЯИКУОЖРЯАОКЦОШНЕЕИВРААПЕЯНЙАКИЗЙЕНЕИПУШМИЩЕИДИНАСТИИКАРБАЛЕТЯ";
    String fillword27 = "СОПАНЩИВТЕЖСЧИНШНОСИИЛДАНЬАЕННВОВИЕРАВЕАОТНОВЕОЕИВЯЛОГОНВННОХШЦДДСТААЗТЕРЫММЕЩДЬАГОИСПОВЕЛВЕРЗЕМЛЕПО";
    String fillword28 = "САМОМАГИСТЦЕХУАВАНИРОДУПЛЛАВКАКОХРАТУШАТРВЕАРРТСИМЕСНВЛЕНИУРСТВОТОРЕБТТНОСТЬГВОЙМЕСТКОРЧМАОКЧЕХОЗЯИН";
    String fillword29 = "САМОСОЗНАНПАРТНАЕЕГИПЛАОЦРПЛЯЕОИФХЕОИЛИКШТИТРДСОШУЛВЯАКНКНЕЦИИНМООООЛЫНАЫЫВСППАГКАЛШЬТЬТНРАГЭТНОСАБО";
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
    int[][] words1 = {w1_1, w1_2, w1_3, w1_4, w1_5, w1_6, w1_7, w1_8, w1_9, w1_10, w1_11, w1_12, w1_13};

    int[] w2_1 = {0, 10, 20, 30, 40, 50, 60, 70, 80};
    int[] w2_2 = {1, 2, 3, 13, 23, 24, 25, 35, 36, 46, 56, 66, 76, 86};
    int[] w2_3 = {4, 14, 15, 16, 26};
    int[] w2_4 = {5, 6, 7, 8, 9, 19, 29, 39, 49, 59};
    int[] w2_5 = {11, 12, 22, 32, 33, 34};
    int[] w2_6 = {21, 31, 41, 42, 43, 44, 45};
    int[] w2_7 = {69, 79, 89, 99};
    int[] w2_8 = {51, 61, 71, 81, 90, 91};
    int[] w2_9 = {17, 18, 27, 28, 38, 48, 58, 68, 78, 88};
    int[] w2_10 = {52, 62, 72, 82, 92};
    int[] w2_11 = {53, 54, 55, 63, 64, 65, 73, 74, 75, 83, 84, 93, 94};
    int[] w2_12 = {37, 47, 57, 67, 77, 87};
    int[] w2_13 = {85, 95, 96, 97, 98};
    int[] w2_14 = {};
    int[] w2_15 = {};
    int[][] words2 = {w2_1, w2_2, w2_3, w2_4, w2_5, w2_6, w2_7, w2_8, w2_9, w2_10, w2_11, w2_12, w2_13, w2_14, w2_15};

    int[] w3_1 = {0, 1, 2, 3, 4, 5};
    int[] w3_2 = {10, 11, 20, 21, 30, 31};
    int[] w3_3 = {6, 7, 8, 9, 19};
    int[] w3_4 = {40, 50, 60, 61, 62, 63, 64, 74};
    int[] w3_5 = {70, 80, 90};
    int[] w3_6 = {71, 72, 73};
    int[] w3_7 = {41, 51, 52, 53, 54};
    int[] w3_8 = {15, 16, 17, 18, 28, 29};
    int[] w3_9 = {12, 13, 14, 24, 25, 26, 27, 37, 38};
    int[] w3_10 = {91, 92, 93, 94, 95, 96};
    int[] w3_11 = {22, 23, 32, 33, 34, 35};
    int[] w3_12 = {36, 42, 43, 44, 45, 46};
    int[] w3_13 = {47, 48, 58, 66, 67, 68, 76, 86, 87, 97, 98, 99};
    int[] w3_14 = {55, 56, 57, 65, 75, 81, 82, 83, 84, 85};
    int[] w3_15 = {39, 49, 59, 69, 77, 78, 79, 88, 89};
    int[][] words3 = {w3_1, w3_2, w3_3, w3_4, w3_5, w3_6, w3_7, w3_8, w3_9, w3_10, w3_11, w3_12, w3_13, w3_14, w3_15};

    int[] w4_1 = {79, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99};
    int[] w4_2 = {68, 69, 78, 87, 88};
    int[] w4_3 = {9, 19, 29, 39, 49, 59};
    int[] w4_4 = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    int[] w4_5 = {10, 20, 30, 40, 50, 60, 70, 80};
    int[] w4_6 = {81, 82, 83, 84, 85};
    int[] w4_7 = {48, 57, 58, 67, 76, 77, 86};
    int[] w4_8 = {11, 12, 13, 14, 15, 16, 17, 18};
    int[] w4_9 = {71, 72, 73, 74, 75};
    int[] w4_10 = {37, 38, 46, 47, 56, 61, 62, 63, 64, 65, 66};
    int[] w4_11 = {51, 52, 53, 54, 55};
    int[] w4_12 = {21, 22, 23, 24, 25, 26, 27, 28};
    int[] w4_13 = {31, 32, 33, 34, 35, 36};
    int[] w4_14 = {41, 42, 43, 44, 45};
    int[] w4_15 = {};
    int[][] words4 = {w4_1, w4_2, w4_3, w4_4, w4_5, w4_6, w4_7, w4_8, w4_9, w4_10, w4_11, w4_12, w4_13, w4_14, w4_15};

    int[] w5_1 = {1, 11, 21, 22, 23, 33, 34, 35, 36};
    int[] w5_2 = {0, 10, 20, 30, 31, 32};
    int[] w5_3 = {9, 19, 29, 39, 49, 59, 69, 79};
    int[] w5_4 = {12, 13, 14, 24};
    int[] w5_5 = {2, 3, 4, 5, 6, 7, 8, 18, 28, 38, 48, 58};
    int[] w5_6 = {15, 16, 17, 25, 26, 27, 37, 47, 57};
    int[] w5_7 = {41, 42, 43, 44, 45, 46};
    int[] w5_8 = {40, 50, 60, 70, 80};
    int[] w5_9 = {68, 78, 88, 89, 99};
    int[] w5_10 = {61, 62, 63, 64, 71, 81, 90, 91};
    int[] w5_11 = {72, 73, 82, 83, 84, 92, 93, 94};
    int[] w5_12 = {51, 52, 53, 54, 55, 56};
    int[] w5_13 = {67, 77, 87, 97, 98};
    int[] w5_14 = {65, 66, 74, 75, 76, 85, 86, 95, 96};
    int[] w5_15 = {};
    int[][] words5 = {w5_1, w5_2, w5_3, w5_4, w5_5, w5_6, w5_7, w5_8, w5_9, w5_10, w5_11, w5_12, w5_13, w5_14, w5_15};

    int[] w6_1 = {20, 21, 30, 40, 50};
    int[] w6_2 = {10, 11, 12, 22};
    int[] w6_3 = {4, 5, 6, 16, 26, 36, 46, 56};
    int[] w6_4 = {7, 8, 9, 19, 29, 39, 49, 59};
    int[] w6_5 = {14, 24, 34, 41, 42, 43, 44};
    int[] w6_6 = {0, 1, 2, 3, 13, 23, 31, 32, 33};
    int[] w6_7 = {15, 25, 35, 45};
    int[] w6_8 = {69, 79, 89, 99};
    int[] w6_9 = {18, 28, 38, 48, 58, 68, 78, 88};
    int[] w6_10 = {51, 52, 53, 54, 55, 60, 61};
    int[] w6_11 = {17, 27, 37, 47, 57, 62, 63, 64, 65, 66, 67, 72};
    int[] w6_12 = {90, 91, 92, 93, 94, 95, 96, 97, 98};
    int[] w6_13 = {70, 71, 80, 81, 82, 83, 84, 85, 86};
    int[] w6_14 = {73, 74, 75, 76, 77, 87};
    int[] w6_15 = {};
    int[][] words6 = {w6_1, w6_2, w6_3, w6_4, w6_5, w6_6, w6_7, w6_8, w6_9, w6_10, w6_11, w6_12, w6_13, w6_14, w6_15};

    int[] w7_1 = {77, 80, 81, 82, 83, 84, 85, 86, 87};
    int[] w7_2 = {50, 51, 60};
    int[] w7_3 = {52, 53, 54, 55, 56, 57, 61, 62, 63, 64, 65, 66, 67, 68, 78, 88, 89};
    int[] w7_4 = {0, 1, 2, 3};
    int[] w7_5 = {9, 19, 29, 39, 49};
    int[] w7_6 = {43, 44, 45, 46};
    int[] w7_7 = {4, 14, 24, 34, 35, 36, 37, 47};
    int[] w7_8 = {90, 91, 92, 93, 94, 95, 96, 97, 98, 99};
    int[] w7_9 = {5, 6, 7, 8, 15, 16, 17, 25, 26, 27};
    int[] w7_10 = {18, 28, 38, 48, 58, 59, 69, 79};
    int[] w7_11 = {10, 11, 12, 13, 23, 32, 33, 42};
    int[] w7_12 = {20, 21, 22, 30, 31, 40, 41};
    int[] w7_13 = {70, 71, 72, 73, 74, 75, 76};
    int[] w7_14 = {};
    int[] w7_15 = {};
    int[][] words7 = {w7_1, w7_2, w7_3, w7_4, w7_5, w7_6, w7_7, w7_8, w7_9, w7_10, w7_11, w7_12, w7_13, w7_14, w7_15};

    int[] w8_1 = {0, 10, 11, 12, 13, 23, 33, 43};
    int[] w8_2 = {90, 91, 92, 93, 94, 95, 96, 97, 98};
    int[] w8_3 = {72, 73, 74, 75, 85};
    int[] w8_4 = {1, 2, 3, 4, 14, 24, 34};
    int[] w8_5 = {80, 81, 82, 83, 84};
    int[] w8_6 = {8, 9, 19, 29, 39, 49, 59, 69, 79, 89, 99};
    int[] w8_7 = {5, 15, 25, 35, 36};
    int[] w8_8 = {20, 30, 40, 50, 60, 70};
    int[] w8_9 = {21, 22, 31, 32, 41, 42, 51, 61, 71};
    int[] w8_10 = {6, 7, 17, 18, 28, 38, 48, 58, 68};
    int[] w8_11 = {44, 45, 52, 53, 54, 55, 62, 63, 64};
    int[] w8_12 = {16, 26, 27, 37, 46, 47, 56, 65, 66};
    int[] w8_13 = {57, 67, 76, 77, 78, 86, 87, 88};
    int[] w8_14 = {};
    int[] w8_15 = {};
    int[][] words8 = {w8_1, w8_2, w8_3, w8_4, w8_5, w8_6, w8_7, w8_8, w8_9, w8_10, w8_11, w8_12, w8_13, w8_14, w8_15};

    int[] w9_1 = {37, 38, 48, 58, 68};
    int[] w9_2 = {56, 66, 76, 86, 87, 96, 97};
    int[] w9_3 = {0, 1, 2, 12, 22};
    int[] w9_4 = {33, 34, 35, 36, 46, 47, 57, 67, 77, 78, 79, 88, 89, 98, 99};
    int[] w9_5 = {10, 11, 21, 31, 32};
    int[] w9_6 = {20, 30, 40, 50, 60, 70, 80};
    int[] w9_7 = {6, 7, 8, 9, 19, 29, 39, 49, 59, 69};
    int[] w9_8 = {41, 42, 43, 44, 45, 51, 55, 61, 65, 71, 81, 90, 91};
    int[] w9_9 = {92, 93, 94, 95};
    int[] w9_10 = {82, 83, 84, 85};
    int[] w9_11 = {52, 62, 72, 73, 74, 75};
    int[] w9_12 = {3, 13, 23, 24, 25};
    int[] w9_13 = {4, 5, 14, 15, 16};
    int[] w9_14 = {53, 54, 63, 64};
    int[] w9_15 = {17, 18, 26, 27, 28};
    int[][] words9 = {w9_1, w9_2, w9_3, w9_4, w9_5, w9_6, w9_7, w9_8, w9_9, w9_10, w9_11, w9_12, w9_13, w9_14, w9_15};

    int[] w10_1 = {0, 1, 2, 3, 4};
    int[] w10_2 = {13, 23, 33, 43, 53, 63, 73, 83, 93};
    int[] w10_3 = {6, 16, 26, 36, 46, 56, 66};
    int[] w10_4 = {10, 20, 30, 40, 50, 60, 70, 80};
    int[] w10_5 = {9, 19, 29, 39, 49, 59, 69, 79, 89};
    int[] w10_6 = {5, 15, 25, 35};
    int[] w10_7 = {14, 24, 34, 44, 45, 55};
    int[] w10_8 = {11, 21, 31, 41, 51, 61, 71};
    int[] w10_9 = {7, 8, 17, 18, 27, 28};
    int[] w10_10 = {38, 48, 58, 68, 78, 88, 98, 99};
    int[] w10_11 = {12, 22, 32, 42, 52, 62, 72};
    int[] w10_12 = {81, 82, 90, 91, 92};
    int[] w10_13 = {37, 47, 57, 67, 77, 87, 97};
    int[] w10_14 = {54, 64, 74, 84, 85, 94, 95};
    int[] w10_15 = {65, 75, 76, 86, 96};
    int[][] words10 = {w10_1, w10_2, w10_3, w10_4, w10_5, w10_6, w10_7, w10_8, w10_9, w10_10, w10_11, w10_12, w10_13, w10_14, w10_15};

    int[] w11_1 = {0, 10, 20, 30, 31, 32, 33};
    int[] w11_2 = {1, 2, 3, 4, 5};
    int[] w11_3 = {9, 19, 29, 39, 49, 58, 59};
    int[] w11_4 = {40, 41, 42, 43, 53, 63, 73, 74, 75, 76, 77, 78};
    int[] w11_5 = {6, 7, 8, 18, 28, 38, 47, 48, 57};
    int[] w11_6 = {90, 91, 92, 93, 94, 95, 96, 97, 98, 99};
    int[] w11_7 = {50, 51, 52, 60, 70, 80};
    int[] w11_8 = {61, 62, 71, 72, 81};
    int[] w11_9 = {11, 12, 13, 14, 15, 16, 17};
    int[] w11_10 = {82, 83, 84, 85, 86, 87, 88, 89};
    int[] w11_11 = {65, 66, 67, 68, 69, 79};
    int[] w11_12 = {21, 22, 23, 24, 34};
    int[] w11_13 = {25, 26, 27, 35, 44, 45};
    int[] w11_14 = {36, 37, 46, 54, 55, 56, 64};
    int[] w11_15 = {};
    int[][] words11 = {w11_1, w11_2, w11_3, w11_4, w11_5, w11_6, w11_7, w11_8, w11_9, w11_10, w11_11, w11_12, w11_13, w11_14, w11_15};

    int[] w12_1 = {21, 22, 23, 24, 25, 26, 27, 28, 29};
    int[] w12_2 = {70, 71, 72, 73, 74, 75, 76, 77, 78, 79};
    int[] w12_3 = {34, 35, 36, 37, 38, 39, 49, 59};
    int[] w12_4 = {89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99};
    int[] w12_5 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 19};
    int[] w12_6 = {33, 43, 44, 45, 46, 56};
    int[] w12_7 = {47, 48, 57, 58, 67, 68, 69};
    int[] w12_8 = {54, 55, 65, 66};
    int[] w12_9 = {80, 81, 82, 83, 84, 85, 86, 87, 88};
    int[] w12_10 = {10, 11, 12, 13, 14, 15, 16, 17, 18, 20, 30};
    int[] w12_11 = {53, 63, 64};
    int[] w12_12 = {31, 32, 40, 41, 42, 50, 51, 52, 60, 61, 62};
    int[] w12_13 = {};
    int[] w12_14 = {};
    int[] w12_15 = {};
    int[][] words12 = {w12_1, w12_2, w12_3, w12_4, w12_5, w12_6, w12_7, w12_8, w12_9, w12_10, w12_11, w12_12, w12_13, w12_14, w12_15};

    int[] w13_1 = {0, 1, 2, 3, 4, 14, 24, 34, 44};
    int[] w13_2 = {5, 6, 7, 8, 18, 28, 38, 48};
    int[] w13_3 = {90, 91, 92, 93, 94, 95, 96, 97, 98};
    int[] w13_4 = {10, 11, 12, 13, 23, 33, 35, 43, 45, 53, 54, 55};
    int[] w13_5 = {9, 19, 29, 39, 49, 59, 69};
    int[] w13_6 = {85, 86, 87, 88, 89, 99};
    int[] w13_7 = {80, 81, 82, 83, 84};
    int[] w13_8 = {17, 27, 37, 47, 57, 58, 67, 68};
    int[] w13_9 = {15, 16, 25, 26, 36};
    int[] w13_10 = {20, 30, 40, 50, 60, 70};
    int[] w13_11 = {46, 56, 63, 64, 65, 66};
    int[] w13_12 = {21, 31, 41, 51};
    int[] w13_13 = {22, 32, 42, 52, 61, 62};
    int[] w13_14 = {75, 76, 77, 78, 79};
    int[] w13_15 = {71, 72, 73, 74};
    int[][] words13 = {w13_1, w13_2, w13_3, w13_4, w13_5, w13_6, w13_7, w13_8, w13_9, w13_10, w13_11, w13_12, w13_13, w13_14, w13_15};

    int[] w14_1 = {57, 67, 77, 83, 84, 85, 86, 87, 90, 91, 92, 93};
    int[] w14_2 = {24, 25, 26, 27, 28, 38};
    int[] w14_3 = {34, 44, 45, 54, 55, 64, 65};
    int[] w14_4 = {59, 69, 79, 89, 94, 95, 96, 97, 98, 99};
    int[] w14_5 = {39, 48, 49, 58, 68, 78, 88};
    int[] w14_6 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    int[] w14_7 = {31, 32, 41, 42, 51, 52, 61, 62, 71};
    int[] w14_8 = {11, 12, 13, 14, 15, 16, 17, 18, 19, 29};
    int[] w14_9 = {46, 47, 56, 63, 66, 73, 74, 75, 76};
    int[] w14_10 = {30, 40, 50, 60, 70, 72, 80, 81, 82};
    int[] w14_11 = {35, 36, 37};
    int[] w14_12 = {0, 10, 20, 21, 22, 23, 33, 43, 53};
    int[] w14_13 = {};
    int[] w14_14 = {};
    int[] w14_15 = {};
    int[][] words14 = {w14_1, w14_2, w14_3, w14_4, w14_5, w14_6, w14_7, w14_8, w14_9, w14_10, w14_11, w14_12, w14_13, w14_14, w14_15};

    int[] w15_1 = {11, 12, 13, 23, 33, 43, 53, 63, 64, 73, 74};
    int[] w15_2 = {0, 1, 2, 3, 4, 14, 24, 34, 44, 54};
    int[] w15_3 = {10, 20, 30, 40, 50};
    int[] w15_4 = {5, 6, 7, 15, 16, 25, 26};
    int[] w15_5 = {60, 61, 70, 71, 80, 81, 90, 91};
    int[] w15_6 = {21, 22, 31, 41, 51};
    int[] w15_7 = {17, 27, 35, 36, 37, 45, 55, 65, 75, 76, 77};
    int[] w15_8 = {8, 9, 19, 29, 39, 49};
    int[] w15_9 = {32, 42, 52, 62, 72, 82, 92, 93, 94};
    int[] w15_10 = {18, 28, 38, 47, 48, 57, 58};
    int[] w15_11 = {83, 84, 85, 86, 95, 96};
    int[] w15_12 = {59, 69, 79, 89, 98, 99};
    int[] w15_13 = {78, 87, 88, 97};
    int[] w15_14 = {46, 56, 66, 67, 68};
    int[] w15_15 = {};
    int[][] words15 = {w15_1, w15_2, w15_3, w15_4, w15_5, w15_6, w15_7, w15_8, w15_9, w15_10, w15_11, w15_12, w15_13, w15_14, w15_15};

    int[] w16_1 = {54, 64, 74, 84, 90, 91, 92, 93, 94};
    int[] w16_2 = {89, 95, 96, 97, 98, 99};
    int[] w16_3 = {0, 10, 11, 12, 13, 14};
    int[] w16_4 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    int[] w16_5 = {20, 30, 40, 50, 60, 70, 73, 80, 81, 82, 83};
    int[] w16_6 = {15, 16, 17, 18, 19};
    int[] w16_7 = {21, 31, 41, 51, 61, 71};
    int[] w16_8 = {26, 27, 28, 29, 39, 49, 59};
    int[] w16_9 = {22, 23, 24, 25, 35, 36, 37, 38, 48, 58};
    int[] w16_10 = {53, 62, 63, 72};
    int[] w16_11 = {78, 79, 85, 86, 87, 88};
    int[] w16_12 = {32, 33, 42, 43, 52};
    int[] w16_13 = {34, 44, 45, 46, 47};
    int[] w16_14 = {67, 68, 69, 75, 76, 77};
    int[] w16_15 = {55, 56, 57, 65, 66};
    int[][] words16 = {w16_1, w16_2, w16_3, w16_4, w16_5, w16_6, w16_7, w16_8, w16_9, w16_10, w16_11, w16_12, w16_13, w16_14, w16_15};

    int[] w17_1 = {11, 12, 13, 23, 33, 43, 53, 54, 55, 56};
    int[] w17_2 = {81, 82, 83, 84, 85, 86, 87, 97};
    int[] w17_3 = {15, 25, 35, 36, 37};
    int[] w17_4 = {0, 1, 2, 3, 4, 14, 24, 34, 44};
    int[] w17_5 = {10, 20, 30, 40, 50, 60, 61};
    int[] w17_6 = {70, 71, 72, 73, 80, 90};
    int[] w17_7 = {52, 62, 63, 64, 74, 75, 76};
    int[] w17_8 = {21, 22, 31, 32, 41, 42, 51};
    int[] w17_9 = {91, 92, 93, 94, 95, 96};
    int[] w17_10 = {45, 46, 47, 48, 49};
    int[] w17_11 = {5, 6, 7, 8, 9, 16, 17, 18, 19};
    int[] w17_12 = {26, 27, 28, 29, 38, 39};
    int[] w17_13 = {77, 78, 79, 88, 89, 98, 99};
    int[] w17_14 = {65, 66, 67, 68, 69};
    int[] w17_15 = {57, 58, 59};
    int[][] words17 = {w17_1, w17_2, w17_3, w17_4, w17_5, w17_6, w17_7, w17_8, w17_9, w17_10, w17_11, w17_12, w17_13, w17_14, w17_15};

    int[] w18_1 = {0, 1, 11, 21, 31, 32};
    int[] w18_2 = {10, 20, 30, 40};
    int[] w18_3 = {2, 3, 4, 14, 15, 16, 17};
    int[] w18_4 = {5, 6, 7, 8, 9, 18, 19};
    int[] w18_5 = {74, 83, 84, 90, 91, 92, 93};
    int[] w18_6 = {12, 13, 22, 23, 24, 25, 26};
    int[] w18_7 = {41, 42, 50, 51, 52};
    int[] w18_8 = {27, 28, 33, 34, 35, 36, 37, 38};
    int[] w18_9 = {69, 79, 89, 94, 95, 96, 97, 98, 99};
    int[] w18_10 = {60, 70, 80, 81, 82};
    int[] w18_11 = {58, 59, 68, 78, 85, 86, 87, 88};
    int[] w18_12 = {29, 39, 47, 48, 49, 57};
    int[] w18_13 = {61, 62, 71, 72, 73};
    int[] w18_14 = {45, 46, 56, 63, 64, 65, 66, 67, 75, 76, 77};
    int[] w18_15 = {43, 44, 53, 54, 55};
    int[][] words18 = {w18_1, w18_2, w18_3, w18_4, w18_5, w18_6, w18_7, w18_8, w18_9, w18_10, w18_11, w18_12, w18_13, w18_14, w18_15};

    int[] w19_1 = {59, 67, 68, 69, 77, 87};
    int[] w19_2 = {13, 14, 15, 23};
    int[] w19_3 = {3, 4, 5, 6, 7, 8, 9};
    int[] w19_4 = {29, 39, 48, 49, 58};
    int[] w19_5 = {16, 17, 18, 19, 27, 28};
    int[] w19_6 = {35, 36, 37, 38, 45, 46, 47, 55};
    int[] w19_7 = {11, 21, 31, 41, 51, 61, 71, 81};
    int[] w19_8 = {56, 57, 66, 76, 83, 84, 85, 86, 90, 91, 92, 93};
    int[] w19_9 = {78, 79, 88, 89, 94, 95, 96, 97, 98, 99};
    int[] w19_10 = {0, 1, 2, 12, 22, 24, 25, 26, 32, 33, 34};
    int[] w19_11 = {44, 54, 64, 65, 72, 73, 74, 75, 82};
    int[] w19_12 = {42, 43, 52, 53, 62, 63};
    int[] w19_13 = {10, 20, 30, 40, 50, 60, 70, 80};
    int[] w19_14 = {};
    int[] w19_15 = {};
    int[][] words19 = {w19_1, w19_2, w19_3, w19_4, w19_5, w19_6, w19_7, w19_8, w19_9, w19_10, w19_11, w19_12, w19_13, w19_14, w19_15};

    int[] w20_1 = {11, 21, 31, 41};
    int[] w20_2 = {0, 1, 2, 3, 4, 5, 6};
    int[] w20_3 = {10, 20, 30, 40, 50, 60, 70};
    int[] w20_4 = {23, 24, 25, 35, 45, 55, 65};
    int[] w20_5 = {12, 22, 32, 33};
    int[] w20_6 = {7, 8, 9, 19, 29, 39};
    int[] w20_7 = {13, 14, 15, 16, 17, 18, 28, 38};
    int[] w20_8 = {90, 91, 92, 93, 94, 95, 96, 97, 98};
    int[] w20_9 = {49, 59, 69, 79, 89, 99};
    int[] w20_10 = {80, 81, 82, 83};
    int[] w20_11 = {48, 58, 68, 78, 84, 85, 86, 87, 88};
    int[] w20_12 = {26, 27, 37, 47, 57};
    int[] w20_13 = {36, 46, 56, 66, 67, 77};
    int[] w20_14 = {34, 42, 43, 44, 51, 52, 61, 71, 72, 73, 74, 75, 76};
    int[] w20_15 = {53, 54, 62, 63, 64};
    int[][] words20 = {w20_1, w20_2, w20_3, w20_4, w20_5, w20_6, w20_7, w20_8, w20_9, w20_10, w20_11, w20_12, w20_13, w20_14, w20_15};

    int[] w21_1 = {56, 66, 76, 85, 86};
    int[] w21_2 = {55, 65, 70, 71, 72, 73, 74, 75};
    int[] w21_3 = {87, 88, 89, 97, 98, 99};
    int[] w21_4 = {90, 91, 92, 93, 94, 95, 96};
    int[] w21_5 = {60, 61, 62, 63, 64};
    int[] w21_6 = {47, 57, 67, 77};
    int[] w21_7 = {2, 3, 4, 5, 6, 7};
    int[] w21_8 = {10, 20, 30, 40, 50, 51};
    int[] w21_9 = {22, 23, 24, 25, 26, 32, 36, 44, 45, 46, 52, 53, 54};
    int[] w21_10 = {0, 1, 11, 21, 31, 33, 34, 35, 41, 42, 43};
    int[] w21_11 = {27, 37, 38, 48, 58, 68, 78};
    int[] w21_12 = {8, 9, 19, 29, 39, 49, 59, 69, 79};
    int[] w21_13 = {12, 13, 14, 15, 16, 17, 18, 28};
    int[] w21_14 = {80, 81, 82, 83, 84};
    int[] w21_15 = {};
    int[][] words21 = {w21_1, w21_2, w21_3, w21_4, w21_5, w21_6, w21_7, w21_8, w21_9, w21_10, w21_11, w21_12, w21_13, w21_14, w21_15};

    int[] w22_1 = {11, 12, 22, 23, 24, 25, 26, 36, 46, 56};
    int[] w22_2 = {0, 10, 20, 21, 31, 32, 33, 34, 35};
    int[] w22_3 = {30, 40, 41, 42, 43, 44, 45};
    int[] w22_4 = {1, 2, 3, 4, 5, 6, 7, 8};
    int[] w22_5 = {50, 51, 60, 61, 70, 80, 90, 91, 92, 93, 94};
    int[] w22_6 = {37, 38, 47, 48, 55, 57, 58, 65, 66, 67};
    int[] w22_7 = {68, 69, 78, 79, 84, 85, 86, 87, 88, 89};
    int[] w22_8 = {63, 73, 81, 82, 83};
    int[] w22_9 = {13, 14, 15, 16, 17, 27};
    int[] w22_10 = {95, 96, 97, 98, 99};
    int[] w22_11 = {9, 18, 19, 28, 29, 39, 49, 59};
    int[] w22_12 = {52, 53, 54, 62, 64, 71, 72, 74, 75, 76, 77};
    int[] w22_13 = {};
    int[] w22_14 = {};
    int[] w22_15 = {};
    int[][] words22 = {w22_1, w22_2, w22_3, w22_4, w22_5, w22_6, w22_7, w22_8, w22_9, w22_10, w22_11, w22_12, w22_13, w22_14, w22_15};

    int[] w23_1 = {53, 54, 55, 56, 66, 76};
    int[] w23_2 = {6, 7, 8, 9, 16, 19, 21, 22, 23, 26, 33, 34, 35, 36};
    int[] w23_3 = {80, 90, 91, 92, 93, 94};
    int[] w23_4 = {17, 18, 28, 29, 39, 49, 59, 69, 78, 79, 88, 89, 98, 99};
    int[] w23_5 = {27, 37, 38, 46, 47, 48, 57, 58};
    int[] w23_6 = {2, 3, 4, 5, 12, 13, 14, 15, 24, 25};
    int[] w23_7 = {63, 64, 65, 73, 74, 75, 81, 82, 83, 84};
    int[] w23_8 = {0, 1, 10, 11, 20, 30, 31, 32};
    int[] w23_9 = {40, 41, 42, 43, 44, 45};
    int[] w23_10 = {67, 68, 77, 85, 86, 87, 95, 96, 97};
    int[] w23_11 = {50, 51, 52, 60, 61, 62, 70, 71, 72};
    int[] w23_12 = {};
    int[] w23_13 = {};
    int[] w23_14 = {};
    int[] w23_15 = {};
    int[][] words23 = {w23_1, w23_2, w23_3, w23_4, w23_5, w23_6, w23_7, w23_8, w23_9, w23_10, w23_11, w23_12, w23_13, w23_14, w23_15};

    int[] w24_1 = {59, 69, 79, 89, 95, 96, 97, 98, 99};
    int[] w24_2 = {0, 10, 20, 21, 22};
    int[] w24_3 = {30, 40, 41, 42, 52};
    int[] w24_4 = {50, 60, 70, 80, 90, 91, 92, 93, 94};
    int[] w24_5 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    int[] w24_6 = {51, 61, 71, 81, 82, 83, 84, 85, 86, 87, 88};
    int[] w24_7 = {11, 12, 13, 14, 15, 16, 17, 18};
    int[] w24_8 = {19, 28, 29, 38, 39, 48, 49};
    int[] w24_9 = {23, 24, 25, 26, 27};
    int[] w24_10 = {31, 32, 33, 34, 35, 36, 37};
    int[] w24_11 = {43, 44, 45, 46, 47};
    int[] w24_12 = {53, 54, 55, 56, 57, 58, 68, 75, 76, 77, 78};
    int[] w24_13 = {62, 63, 64, 65, 66, 67, 72, 73, 74};
    int[] w24_14 = {};
    int[] w24_15 = {};
    int[][] words24 = {w24_1, w24_2, w24_3, w24_4, w24_5, w24_6, w24_7, w24_8, w24_9, w24_10, w24_11, w24_12, w24_13, w24_14, w24_15};

    int[] w25_1 = {0, 10, 20, 21, 22, 23, 24, 25, 26, 27, 28};
    int[] w25_2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    int[] w25_3 = {30, 40, 50, 51, 52, 53, 54, 55, 56};
    int[] w25_4 = {60, 70, 80, 90, 91, 92};
    int[] w25_5 = {31, 32, 33, 34, 35, 36, 37};
    int[] w25_6 = {71, 81, 82, 83, 84, 93, 94};
    int[] w25_7 = {11, 12, 13, 14, 15, 16, 17, 18, 19};
    int[] w25_8 = {29, 39, 49, 59, 69, 79, 89, 95, 96, 97, 98, 99};
    int[] w25_9 = {41, 42, 43, 44, 45, 46, 47};
    int[] w25_10 = {57, 61, 62, 63, 64, 65, 66, 67};
    int[] w25_11 = {85, 86, 87, 88};
    int[] w25_12 = {72, 73, 74, 75, 76, 77, 78};
    int[] w25_13 = {38, 48, 58, 68};
    int[] w25_14 = {};
    int[] w25_15 = {};
    int[][] words25 = {w25_1, w25_2, w25_3, w25_4, w25_5, w25_6, w25_7, w25_8, w25_9, w25_10, w25_11, w25_12, w25_13, w25_14, w25_15};

    int[] w26_1 = {22, 23, 33, 43, 53, 63};
    int[] w26_2 = {32, 42, 52, 62, 72, 81, 82};
    int[] w26_3 = {41, 51, 61, 70, 71, 80, 90, 91};
    int[] w26_4 = {1, 2, 11, 21, 31};
    int[] w26_5 = {92, 93, 94, 95, 96, 97, 98};
    int[] w26_6 = {12, 13, 14, 24, 34, 44, 54, 64, 73, 74};
    int[] w26_7 = {3, 4, 5, 6, 7, 8, 9, 19, 29, 39};
    int[] w26_8 = {0, 10, 20, 30, 40, 50, 60};
    int[] w26_9 = {83, 84, 85, 86, 87, 88, 89, 99};
    int[] w26_10 = {15, 16, 17, 18, 28, 38, 48};
    int[] w26_11 = {25, 26, 27, 37, 46, 47, 56, 57};
    int[] w26_12 = {66, 67, 75, 76, 77};
    int[] w26_13 = {35, 36, 45, 55, 65};
    int[] w26_14 = {49, 58, 59, 68, 69, 78, 79};
    int[] w26_15 = {};
    int[][] words26 = {w26_1, w26_2, w26_3, w26_4, w26_5, w26_6, w26_7, w26_8, w26_9, w26_10, w26_11, w26_12, w26_13, w26_14, w26_15};

    int[] w27_1 = {10, 20, 30, 40, 41, 42, 43, 44, 46, 54, 56, 64, 65, 66};
    int[] w27_2 = {0, 1, 11, 21, 31, 32, 33, 34};
    int[] w27_3 = {38, 48, 58, 68, 78, 82, 83, 84, 85, 86, 87, 88, 90, 91, 92};
    int[] w27_4 = {52, 53, 62, 71, 72, 81};
    int[] w27_5 = {50, 51, 60, 61, 70, 80};
    int[] w27_6 = {47, 57, 67, 75, 76, 77};
    int[] w27_7 = {63, 73, 74};
    int[] w27_8 = {9, 19, 29, 39, 49, 59, 69, 79, 89, 93, 94, 95, 96, 97, 98, 99};
    int[] w27_9 = {7, 8, 17, 18, 27, 28, 35, 36, 37, 45, 55};
    int[] w27_10 = {12, 13, 14, 15};
    int[] w27_11 = {22, 23, 24, 25};
    int[] w27_12 = {2, 3, 4, 5, 6, 16, 26};
    int[] w27_13 = {};
    int[] w27_14 = {};
    int[] w27_15 = {};
    int[][] words27 = {w27_1, w27_2, w27_3, w27_4, w27_5, w27_6, w27_7, w27_8, w27_9, w27_10, w27_11, w27_12, w27_13, w27_14, w27_15};

    int[] w28_1 = {64, 65, 66, 76};
    int[] w28_2 = {20, 30, 40, 50, 60, 70, 71, 72, 73, 74, 75};
    int[] w28_3 = {21, 22, 31, 32, 41, 42, 51, 52, 61, 62, 63};
    int[] w28_4 = {80, 81, 82, 83, 90, 91, 92, 93};
    int[] w28_5 = {94, 95, 96, 97, 98, 99};
    int[] w28_6 = {10, 11, 12};
    int[] w28_7 = {84, 85, 86, 87, 88, 89};
    int[] w28_8 = {0, 1, 2, 3, 13, 23, 33, 43, 53, 54, 55, 56, 57, 67};
    int[] w28_9 = {4, 5, 6, 7, 8, 9, 19, 29, 39};
    int[] w28_10 = {14, 15, 16, 24};
    int[] w28_11 = {69, 77, 78, 79};
    int[] w28_12 = {45, 46, 47, 48, 49, 58, 59, 68};
    int[] w28_13 = {34, 35, 36, 37, 38, 44};
    int[] w28_14 = {17, 18, 25, 26, 27, 28};
    int[] w28_15 = {};
    int[][] words28 = {w28_1, w28_2, w28_3, w28_4, w28_5, w28_6, w28_7, w28_8, w28_9, w28_10, w28_11, w28_12, w28_13, w28_14, w28_15};

    int[] w29_1 = {24, 34, 44, 54, 64, 74, 84};
    int[] w29_2 = {92, 93, 94, 95, 96};
    int[] w29_3 = {14, 15, 25, 35, 45, 55, 65, 75, 85, 86};
    int[] w29_4 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 19, 29};
    int[] w29_5 = {13, 23, 33, 43, 53, 63, 73, 83};
    int[] w29_6 = {80, 81, 82, 90, 91};
    int[] w29_7 = {87, 89, 97, 98, 99};
    int[] w29_8 = {59, 69, 78, 79, 88};
    int[] w29_9 = {10, 11, 12, 22, 32, 42, 52};
    int[] w29_10 = {16, 26, 36, 46, 56, 66, 76};
    int[] w29_11 = {21, 31, 41, 51, 61, 62, 72};
    int[] w29_12 = {38, 39, 48, 49, 58, 67, 68, 77};
    int[] w29_13 = {17, 18, 27, 28, 37, 47, 57};
    int[] w29_14 = {20, 30, 40, 50, 60, 70, 71};
    int[] w29_15 = {};
    int[][] words29 = {w29_1, w29_2, w29_3, w29_4, w29_5, w29_6, w29_7, w29_8, w29_9, w29_10, w29_11, w29_12, w29_13, w29_14, w29_15};

    int[] w30_1 = {0, 1, 11, 21, 31};
    int[] w30_2 = {10, 20, 30, 40, 41, 42};
    int[] w30_3 = {2, 3, 4, 5, 6, 7, 8};
    int[] w30_4 = {50, 51, 52, 62, 72, 73, 74, 75, 76};
    int[] w30_5 = {9, 19, 29, 39, 49, 59, 69, 79, 89};
    int[] w30_6 = {60, 70, 80, 90, 91};
    int[] w30_7 = {61, 71, 81, 82, 83, 84, 92, 93, 94};
    int[] w30_8 = {85, 86, 87, 88, 95, 96, 97, 98, 99};
    int[] w30_9 = {12, 13, 14, 15, 16, 17, 18, 28};
    int[] w30_10 = {27, 37, 38, 48, 58, 66, 67, 68, 77, 78};
    int[] w30_11 = {22, 23, 24, 25, 26, 36, 46, 47, 55, 56, 57, 65};
    int[] w30_12 = {32, 33, 43, 53, 63};
    int[] w30_13 = {34, 35, 44, 45, 54, 64};
    int[] w30_14 = {};
    int[] w30_15 = {};
    int[][] words30 = {w30_1, w30_2, w30_3, w30_4, w30_5, w30_6, w30_7, w30_8, w30_9, w30_10, w30_11, w30_12, w30_13, w30_14, w30_15};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_word_2);

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

        //grid = findViewById(R.id.grid1);
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


        /**-------------------------------------*/
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Display display = getWindowManager().getDefaultDisplay();
            DisplayMetrics outMetrics = new DisplayMetrics ();
            display.getMetrics(outMetrics);

            float density  = getResources().getDisplayMetrics().density;
            float dpHeight = outMetrics.heightPixels;
            float dpWidth  = outMetrics.widthPixels;
            float dptext = outMetrics.widthPixels / 10 / density - 20;

            int tvsize = (int) dpWidth / 10 - 5;

            for (int i = 0 ; i < tvs.size(); i++){
                tvs.get(i).setWidth(tvsize);
                tvs.get(i).setHeight(tvsize);
                tvs.get(i).setTextSize(TypedValue.COMPLEX_UNIT_DIP, dptext);
            }
        } else {
            Display display = getWindowManager().getDefaultDisplay();
            DisplayMetrics outMetrics = new DisplayMetrics ();
            display.getMetrics(outMetrics);

            float density  = getResources().getDisplayMetrics().density;
            float dpWidth  = outMetrics.heightPixels;
            float dptext = outMetrics.widthPixels / 10 / density - 150;

            int tvsize = (int) dpWidth / 10 - 30;

            for (int i = 0 ; i < tvs.size(); i++){
                tvs.get(i).setWidth(tvsize);
                tvs.get(i).setHeight(tvsize);
                tvs.get(i).setTextSize(TypedValue.COMPLEX_UNIT_DIP, dptext);
            }
        }

        /*Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);

        float density  = getResources().getDisplayMetrics().density;
        float dpHeight = outMetrics.heightPixels;
        float dpWidth  = outMetrics.widthPixels;
        float dptext = outMetrics.widthPixels / 10 / density - 15;

        int tvsize = (int) dpWidth / 10 - 5;

        for (int i = 0 ; i < tvs.size(); i++){
            tvs.get(i).setWidth(tvsize);
            tvs.get(i).setHeight(tvsize);
            tvs.get(i).setTextSize(TypedValue.COMPLEX_UNIT_DIP, dptext);
        }*/

        /**----------------------------------------------*/
        switch (taskId) {
            case 0: {
                fillword = fillword1;
                words = words1;
                break;
            }
            case 1: {
                fillword = fillword2;
                words = words2;
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
                Log.i("word", checked.toString());
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
    protected void onResume() {
        super.onResume();
        /**-------------------------------------*/
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Display display = getWindowManager().getDefaultDisplay();
            DisplayMetrics outMetrics = new DisplayMetrics ();
            display.getMetrics(outMetrics);

            float density  = getResources().getDisplayMetrics().density;
            float dpHeight = outMetrics.heightPixels;
            float dpWidth  = outMetrics.widthPixels;
            float dptext = outMetrics.widthPixels / 10 / density - 20;

            int tvsize = (int) dpWidth / 10 - 5;

            for (int i = 0 ; i < tvs.size(); i++){
                tvs.get(i).setWidth(tvsize);
                tvs.get(i).setHeight(tvsize);
                tvs.get(i).setTextSize(TypedValue.COMPLEX_UNIT_DIP, dptext);
            }
        } else {
            Display display = getWindowManager().getDefaultDisplay();
            DisplayMetrics outMetrics = new DisplayMetrics ();
            display.getMetrics(outMetrics);

            float density  = getResources().getDisplayMetrics().density;
            float dpWidth  = outMetrics.heightPixels;
            float dptext = outMetrics.widthPixels / 10 / density - 150;

            int tvsize = (int) dpWidth / 10 - 30;

            for (int i = 0 ; i < tvs.size(); i++){
                tvs.get(i).setWidth(tvsize);
                tvs.get(i).setHeight(tvsize);
                tvs.get(i).setTextSize(TypedValue.COMPLEX_UNIT_DIP, dptext);
            }
        }

    }

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
            } else{
                checked.remove(checked.indexOf(tvs.indexOf(view)));
                tvs.get(tvs.indexOf(view)).setBackgroundColor(ContextCompat.getColor(context, R.color.colorBack));

            }

            Collections.sort(checked, new Comparator<Integer>() {
                public int compare(Integer o1, Integer o2) {
                    return o1.compareTo(o2);
                }
            });

            //te1.setText(checked.toString());

            for (int j = 0; j < words.length; j++) {
                if (checked.equals(getArrayList(words[j]))) {
                    //Toast.makeText(getApplicationContext(), "slovo", Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < checked.size(); i++) {
                        tvs.get(checked.get(i)).setBackgroundColor(getResources().getColor(colorsF[j]));
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
        for (int value : a1) {
            array.add(value);
        }
        return array;
    }
}
