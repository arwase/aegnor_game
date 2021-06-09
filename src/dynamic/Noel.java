package dynamic;

import client.Player;
import common.Formulas;
import common.SocketManager;
import game.world.World;
import object.GameObject;

public class Noel {

    private static String objectOne = "274,287,288,300,301,304,305,306,307,309,310,312,315,316,360,362,363,364,365,366,367,368,369,370,373,374,375,376,379,380,381,382,383,384,387,388,389,390,391,392,393,394,395,396,397,398,399,407,408,409,411,412,413,414,415,416,418,419,421,422,424,426,427,428,430,431,432,433,434,435,437,441,442,443,444,445,447,448,450,463,464,465,466,467,479,480,485,486,543,544,545,546,547,578,593,594,598,600,603,607,646,648,649,650,652,653,654,678,679,680,683,684,686,695,713,714,715,716,717,724,725,726,727,728,729,730,745,746,747,748,749,756,757,761,795,796,797,798,799,800,801,802,803,804,805,806,807,808,809,810,811,812,814,815,816,817,878,879,880,881,882,883,884,885,886,887,901,965,997,999,1022,1141,1142,1324,1325,1326,1327,1332,1336,1339,1344,1428,1455,1456,1457,1458,1515,1516,1517,1518,1526,1527,1528,1529,1531,1532,1533,1534,1535,1536,1537,1538,1568,1569,1570,1634,1652,1663,1664,1670,1672,1673,1675,1678,1679,1681,1685,1690,1709,1734,1736,1750,1754,1757,1759,1762,1772,1774,1776,1778,1779,1782,1784,1786,1788,1790,1794,1796,1799,1805,1807,1844,1846,1847,1849,1889,1891,1893,1894,1973,2056,2060,2150,2179,2187,2245,2249,2251,2252,2253,2254,2259,2264,2271,2273,2275,2278,2279,2281,2283,2285,2288,2290,2291,2292,2293,2300,2303,2304,2305,2306,2315,2316,2317,2318,2322,2323,2324,2325,2326,2327,2329,2331,2336,2365,2436,2451,2453,2460,2463,2464,2465,2467,2480,2483,2486,2487,2490,2495,2496,2497,2501,2502,2503,2509,2511,2512,2513,2514,2516,2554,2555,2557,2571,2573,2579,2583,2586,2588,2591,2594,2598,2599,2602,2605,2609,2610,2611,2613,2624,2642,2646,2647,2654,2659,2661,2664,2665,2673,2674,2675,3208,6671,6735,6736,6737,6739,6818,6841,6842,6843,6844,6845,6884,6897,6898,6899,6900,6902,6903,7222,7223,7224,7225,7258,7259,7260,7262,7263,7266,7267,7270,7271,7275,7279,7285,7287,7290,7301,7309,7310,7311,7312,7343,7369,7508,7509,7510,7511,7557,7651,7903,7904,7905,7906,7907,7908,7918,7924,7926,7927,8002,8050,8052,8053,8054,8055,8056,8057,8058,8059,8060,8061,8062,8063,8065,8066,8073,8082,8083,8084,8085,8087,8135,8138,8139,8141,8142,8143,8156,8157,8158,8249,8250,8251,8252,8307,8308,8309,8310,8311,8312,8313,8314,8315,8320,8342,8343,8344,8345,8346,8347,8348,8349,8350,8351,8352,8353,8354,8355,8390,8430,8435,8436,8437,8438,8439,8481,8482,8483,8484,8515,8520,8545,8546,8557,8570,8571,8621,8624,8625,8626,8680,8681,8682,8712,8729,8730,8732,8733,8734,8735,8744,8745,8746,8747,8748,8749,8750,8751,8752,8917,8971,8972,8975,8977,9077,9078,9079,9080,9081,9082,9083,9084,9085,9086,9087,9088,9247,9248,9249,9250,9251,9252,9254,9381,9382,9383,9622,9940,9941,9949,9950,9951,9952,9953,9954,9955,9999,10003,10382,10383,10384,10385,10386,10387,10388,10389,10390,10391,10392,10393,10394,10395,10396,10397,10398,10399,10400,10401,10402,10403,10404,10405,10407,7413,1635,361,10563";

    private static String objectTwo = "274,287,288,300,301,304,305,306,307,309,310,312,315,316,360,362,363,364,365,366,367,368,369,370,373,374,375,376,379,380,381,382,383,384,387,388,389,390,391,392,393,394,395,396,397,398,399,407,408,409,411,412,413,414,415,416,418,419,421,422,424,426,427,428,430,431,432,433,434,435,437,441,442,443,444,445,447,448,450,463,464,465,466,467,479,480,485,486,543,544,545,546,547,578,593,594,598,600,603,607,646,648,649,650,652,653,654,678,679,680,683,684,686,695,713,714,715,716,717,724,725,726,727,728,729,730,745,746,747,748,749,756,757,761,795,796,797,798,799,800,801,802,803,804,805,806,807,808,809,810,811,812,814,815,816,817,878,879,880,881,882,883,884,885,886,887,901,965,997,999,1022,1141,1142,1324,1325,1326,1327,1332,1336,1339,1344,1428,1455,1456,1457,1458,1515,1516,1517,1518,1526,1527,1528,1529,1531,1532,1533,1534,1535,1536,1537,1538,1568,1569,1570,1634,1652,1663,1664,1670,1672,1673,1675,1678,1679,1681,1685,1690,1709,1734,1736,1750,1754,1757,1759,1762,1772,1774,1776,1778,1779,1782,1784,1786,1788,1790,1794,1796,1799,1805,1807,1844,1846,1847,1849,1889,1891,1893,1894,1973,2056,2060,2150,2179,2187,2245,2249,2251,2252,2253,2254,2259,2264,2271,2273,2275,2278,2279,2281,2283,2285,2288,2290,2291,2292,2293,2300,2303,2304,2305,2306,2315,2316,2317,2318,2322,2323,2324,2325,2326,2327,2329,2331,2336,2365,2436,2451,2453,2460,2463,2464,2465,2467,2480,2483,2486,2487,2490,2495,2496,2497,2501,2502,2503,2509,2511,2512,2513,2514,2516,2554,2555,2557,2571,2573,2579,2583,2586,2588,2591,2594,2598,2599,2602,2605,2609,2610,2611,2613,2624,2642,2646,2647,2654,2659,2661,2664,2665,2673,2674,2675,3208,6671,6735,6736,6737,6739,6818,6841,6842,6843,6844,6845,6884,6897,6898,6899,6900,6902,6903,7222,7223,7224,7225,7258,7259,7260,7262,7263,7266,7267,7270,7271,7275,7279,7285,7287,7290,7301,7309,7310,7311,7312,7343,7369,7508,7509,7510,7511,7557,7651,7903,7904,7905,7906,7907,7908,7918,7924,7926,7927,8002,8050,8052,8053,8054,8055,8056,8057,8058,8059,8060,8061,8062,8063,8065,8066,8073,8082,8083,8084,8085,8087,8135,8138,8139,8141,8142,8143,8156,8157,8158,8249,8250,8251,8252,8307,8308,8309,8310,8311,8312,8313,8314,8315,8320,8342,8343,8344,8345,8346,8347,8348,8349,8350,8351,8352,8353,8354,8355,8390,8430,8435,8436,8437,8438,8439,8481,8482,8483,8484,8515,8520,8545,8546,8557,8570,8571,8621,8624,8625,8626,8680,8681,8682,8712,8729,8730,8732,8733,8734,8735,8744,8745,8746,8747,8748,8749,8750,8751,8752,8917,8971,8972,8975,8977,9077,9078,9079,9080,9081,9082,9083,9084,9085,9086,9087,9088,9247,9248,9249,9250,9251,9252,9254,9381,9382,9383,9622,9940,9941,9949,9950,9951,9952,9953,9954,9955,9999,10003,10382,10383,10384,10385,10386,10387,10388,10389,10390,10391,10392,10393,10394,10395,10396,10397,10398,10399,10400,10401,10402,10403,10404,10405,10407,7413,1635,361,10563"
            + ",291,308,313,350,438,439,446,481,487,602,651,750,842,843,844,845,846,847,848,918,1129,1610,1611,1612,1613,1614,1660,1682,1792,1801,1803,1853,1890,2241,2247,2248,2277,2287,2491,2528,2550,2551,2552,2578,2643,2644,6457,6458,6738,6740,7023,7024,7025,7026,7027,7028,7030,7032,7033,7035,7036,7059,7264,7268,7272,7273,7274,7277,7278,7281,7282,7283,7284,7291,7303,7308,7379,7380,7381,7382,7383,7384,7385,7393,7403,7404,7405,7406,7407,7408,7410,7411,7799,8159,8161,8321,8322,8323,8324,8327,8328,8356,8357,8358,8359,8360,8361,8362,8363,8369,8370,8371,8372,8373,8374,8375,8376,8385,8386,8388,8389,8391,8392,8393,8394,8397,8398,8399,8400,8401,8402,8407,8485,8486,8488,8489,8491,8492,8736,8737,8739,8740,8741,8753,8754,8755,8756,8762,8766,8767,8768,8769,8773,8774,8775,8778,8779,8780,8781,8782,8785,8799,8801,8916,9384,9385,9386,9387,9388,8335";

    private static String objectTree = "274,287,288,300,301,304,305,306,307,309,310,312,315,316,360,362,363,364,365,366,367,368,369,370,373,374,375,376,379,380,381,382,383,384,387,388,389,390,391,392,393,394,395,396,397,398,399,407,408,409,411,412,413,414,415,416,418,419,421,422,424,426,427,428,430,431,432,433,434,435,437,441,442,443,444,445,447,448,450,463,464,465,466,467,479,480,485,486,543,544,545,546,547,578,593,594,598,600,603,607,646,648,649,650,652,653,654,678,679,680,683,684,686,695,713,714,715,716,717,724,725,726,727,728,729,730,745,746,747,748,749,756,757,761,795,796,797,798,799,800,801,802,803,804,805,806,807,808,809,810,811,812,814,815,816,817,878,879,880,881,882,883,884,885,886,887,901,965,997,999,1022,1141,1142,1324,1325,1326,1327,1332,1336,1339,1344,1428,1455,1456,1457,1458,1515,1516,1517,1518,1526,1527,1528,1529,1531,1532,1533,1534,1535,1536,1537,1538,1568,1569,1570,1634,1652,1663,1664,1670,1672,1673,1675,1678,1679,1681,1685,1690,1709,1734,1736,1750,1754,1757,1759,1762,1772,1774,1776,1778,1779,1782,1784,1786,1788,1790,1794,1796,1799,1805,1807,1844,1846,1847,1849,1889,1891,1893,1894,1973,2056,2060,2150,2179,2187,2245,2249,2251,2252,2253,2254,2259,2264,2271,2273,2275,2278,2279,2281,2283,2285,2288,2290,2291,2292,2293,2300,2303,2304,2305,2306,2315,2316,2317,2318,2322,2323,2324,2325,2326,2327,2329,2331,2336,2365,2436,2451,2453,2460,2463,2464,2465,2467,2480,2483,2486,2487,2490,2495,2496,2497,2501,2502,2503,2509,2511,2512,2513,2514,2516,2554,2555,2557,2571,2573,2579,2583,2586,2588,2591,2594,2598,2599,2602,2605,2609,2610,2611,2613,2624,2642,2646,2647,2654,2659,2661,2664,2665,2673,2674,2675,3208,6671,6735,6736,6737,6739,6818,6841,6842,6843,6844,6845,6884,6897,6898,6899,6900,6902,6903,7222,7223,7224,7225,7258,7259,7260,7262,7263,7266,7267,7270,7271,7275,7279,7285,7287,7290,7301,7309,7310,7311,7312,7343,7369,7508,7509,7510,7511,7557,7651,7903,7904,7905,7906,7907,7908,7918,7924,7926,7927,8002,8050,8052,8053,8054,8055,8056,8057,8058,8059,8060,8061,8062,8063,8065,8066,8073,8082,8083,8084,8085,8087,8135,8138,8139,8141,8142,8143,8156,8157,8158,8249,8250,8251,8252,8307,8308,8309,8310,8311,8312,8313,8314,8315,8320,8342,8343,8344,8345,8346,8347,8348,8349,8350,8351,8352,8353,8354,8355,8390,8430,8435,8436,8437,8438,8439,8481,8482,8483,8484,8515,8520,8545,8546,8557,8570,8571,8621,8624,8625,8626,8680,8681,8682,8712,8729,8730,8732,8733,8734,8735,8744,8745,8746,8747,8748,8749,8750,8751,8752,8917,8971,8972,8975,8977,9077,9078,9079,9080,9081,9082,9083,9084,9085,9086,9087,9088,9247,9248,9249,9250,9251,9252,9254,9381,9382,9383,9622,9940,9941,9949,9950,9951,9952,9953,9954,9955,9999,10003,10382,10383,10384,10385,10386,10387,10388,10389,10390,10391,10392,10393,10394,10395,10396,10397,10398,10399,10400,10401,10402,10403,10404,10405,10407,7413,1635,361,10563"
            + ",291,308,313,350,438,439,446,481,487,602,651,750,842,843,844,845,846,847,848,918,1129,1610,1611,1612,1613,1614,1660,1682,1792,1801,1803,1853,1890,2241,2247,2248,2277,2287,2491,2528,2550,2551,2552,2578,2643,2644,6457,6458,6738,6740,7023,7024,7025,7026,7027,7028,7030,7032,7033,7035,7036,7059,7264,7268,7272,7273,7274,7277,7278,7281,7282,7283,7284,7291,7303,7308,7379,7380,7381,7382,7383,7384,7385,7393,7403,7404,7405,7406,7407,7408,7410,7411,7799,8159,8161,8321,8322,8323,8324,8327,8328,8356,8357,8358,8359,8360,8361,8362,8363,8369,8370,8371,8372,8373,8374,8375,8376,8385,8386,8388,8389,8391,8392,8393,8394,8397,8398,8399,8400,8401,8402,8407,8485,8486,8488,8489,8491,8492,8736,8737,8739,8740,8741,8753,8754,8755,8756,8762,8766,8767,8768,8769,8773,8774,8775,8778,8779,8780,8781,8782,8785,8799,8801,8916,9384,9385,9386,9387,9388,8335"
            + ",2411,2414,2416,2419,2422,2425,2428,2074,2409,2412,2415,2418,2421,2424,2427,2410,2413,2417,2420,2423,2426,2429,2438,2440,2441,2442,2443,2444,2445,2473,2474,2475,2476,2477,2478,2446,2447,2469,2470,2471,2472,2640,2641,2544,2545,2546,6449,7703,2530,2531,2532,7714,2485,2489,2498,6719,6720,6721,6722,6723,6724,6725,2075,2534,2535,2537,6731,6732,6750,6753,2077,6746,6748,6751,6754,6756,6758,6760,2076,6747,6749,6752,6755,6757,6759,6761,6766,6767,6769,6774,6775,6776,6778,6799,6804,6805,6807,6811,6812,6813,6908,6909,6910,6911,6912,6913,6914,6915,6916,6917,6918,6919,6920,6921,6922,6923,6924,6925,6961,6930,6931,6933,6934,6935,6957,6936,6940,6944,6948,6937,6942,6945,6950,6938,6943,6946,6949,6939,6941,6947,6951,6926,6927,6928,6929,6952,6953,6954,6955,6956,8129,8130,7106,7107,7108,7109,7110,7226,7230,7238,7242,7246,7250,7254,9004,7229,7233,7241,7245,7249,7253,7256,9030,7227,7231,7239,7243,7247,7251,7255,9028,7228,7232,7240,7244,7248,7252,7257,9029,969,970,971,7339,7340,7341,7342,7911,8003,8004,8005,8006,8007,8008,8009,8123,8124,8125,8126,8127,8128,8131,8132,8133,8134,8136,8108,8109,8110,8111,8112,8113,8114,8116,8117,8118,8119,8120,8121,8122,8213,8219,8225,8231,8237,8243,8214,8220,8226,8232,8238,8244,8215,8221,8227,8234,8239,8245,8218,8224,8230,8235,8242,8248,8216,8222,8228,8233,8240,8246,8217,8223,8229,8236,8241,8247,8336";

    private static String objectFour = "274,287,288,300,301,304,305,306,307,309,310,312,315,316,360,362,363,364,365,366,367,368,369,370,373,374,375,376,379,380,381,382,383,384,387,388,389,390,391,392,393,394,395,396,397,398,399,407,408,409,411,412,413,414,415,416,418,419,421,422,424,426,427,428,430,431,432,433,434,435,437,441,442,443,444,445,447,448,450,463,464,465,466,467,479,480,485,486,543,544,545,546,547,578,593,594,598,600,603,607,646,648,649,650,652,653,654,678,679,680,683,684,686,695,713,714,715,716,717,724,725,726,727,728,729,730,745,746,747,748,749,756,757,761,795,796,797,798,799,800,801,802,803,804,805,806,807,808,809,810,811,812,814,815,816,817,878,879,880,881,882,883,884,885,886,887,901,965,997,999,1022,1141,1142,1324,1325,1326,1327,1332,1336,1339,1344,1428,1455,1456,1457,1458,1515,1516,1517,1518,1526,1527,1528,1529,1531,1532,1533,1534,1535,1536,1537,1538,1568,1569,1570,1634,1652,1663,1664,1670,1672,1673,1675,1678,1679,1681,1685,1690,1709,1734,1736,1750,1754,1757,1759,1762,1772,1774,1776,1778,1779,1782,1784,1786,1788,1790,1794,1796,1799,1805,1807,1844,1846,1847,1849,1889,1891,1893,1894,1973,2056,2060,2150,2179,2187,2245,2249,2251,2252,2253,2254,2259,2264,2271,2273,2275,2278,2279,2281,2283,2285,2288,2290,2291,2292,2293,2300,2303,2304,2305,2306,2315,2316,2317,2318,2322,2323,2324,2325,2326,2327,2329,2331,2336,2365,2436,2451,2453,2460,2463,2464,2465,2467,2480,2483,2486,2487,2490,2495,2496,2497,2501,2502,2503,2509,2511,2512,2513,2514,2516,2554,2555,2557,2571,2573,2579,2583,2586,2588,2591,2594,2598,2599,2602,2605,2609,2610,2611,2613,2624,2642,2646,2647,2654,2659,2661,2664,2665,2673,2674,2675,3208,6671,6735,6736,6737,6739,6818,6841,6842,6843,6844,6845,6884,6897,6898,6899,6900,6902,6903,7222,7223,7224,7225,7258,7259,7260,7262,7263,7266,7267,7270,7271,7275,7279,7285,7287,7290,7301,7309,7310,7311,7312,7343,7369,7508,7509,7510,7511,7557,7651,7903,7904,7905,7906,7907,7908,7918,7924,7926,7927,8002,8050,8052,8053,8054,8055,8056,8057,8058,8059,8060,8061,8062,8063,8065,8066,8073,8082,8083,8084,8085,8087,8135,8138,8139,8141,8142,8143,8156,8157,8158,8249,8250,8251,8252,8307,8308,8309,8310,8311,8312,8313,8314,8315,8320,8342,8343,8344,8345,8346,8347,8348,8349,8350,8351,8352,8353,8354,8355,8390,8430,8435,8436,8437,8438,8439,8481,8482,8483,8484,8515,8520,8545,8546,8557,8570,8571,8621,8624,8625,8626,8680,8681,8682,8712,8729,8730,8732,8733,8734,8735,8744,8745,8746,8747,8748,8749,8750,8751,8752,8917,8971,8972,8975,8977,9077,9078,9079,9080,9081,9082,9083,9084,9085,9086,9087,9088,9247,9248,9249,9250,9251,9252,9254,9381,9382,9383,9622,9940,9941,9949,9950,9951,9952,9953,9954,9955,9999,10003,10382,10383,10384,10385,10386,10387,10388,10389,10390,10391,10392,10393,10394,10395,10396,10397,10398,10399,10400,10401,10402,10403,10404,10405,10407,7413,1635,361,10563"
            + ",291,308,313,350,438,439,446,481,487,602,651,750,842,843,844,845,846,847,848,918,1129,1610,1611,1612,1613,1614,1660,1682,1792,1801,1803,1853,1890,2241,2247,2248,2277,2287,2491,2528,2550,2551,2552,2578,2643,2644,6457,6458,6738,6740,7023,7024,7025,7026,7027,7028,7030,7032,7033,7035,7036,7059,7264,7268,7272,7273,7274,7277,7278,7281,7282,7283,7284,7291,7303,7308,7379,7380,7381,7382,7383,7384,7385,7393,7403,7404,7405,7406,7407,7408,7410,7411,7799,8159,8161,8321,8322,8323,8324,8327,8328,8356,8357,8358,8359,8360,8361,8362,8363,8369,8370,8371,8372,8373,8374,8375,8376,8385,8386,8388,8389,8391,8392,8393,8394,8397,8398,8399,8400,8401,8402,8407,8485,8486,8488,8489,8491,8492,8736,8737,8739,8740,8741,8753,8754,8755,8756,8762,8766,8767,8768,8769,8773,8774,8775,8778,8779,8780,8781,8782,8785,8799,8801,8916,9384,9385,9386,9387,9388,8335"
            + ",2411,2414,2416,2419,2422,2425,2428,2074,2409,2412,2415,2418,2421,2424,2427,2410,2413,2417,2420,2423,2426,2429,2438,2440,2441,2442,2443,2444,2445,2473,2474,2475,2476,2477,2478,2446,2447,2469,2470,2471,2472,2640,2641,2544,2545,2546,6449,7703,2530,2531,2532,7714,2485,2489,2498,6719,6720,6721,6722,6723,6724,6725,2075,2534,2535,2537,6731,6732,6750,6753,2077,6746,6748,6751,6754,6756,6758,6760,2076,6747,6749,6752,6755,6757,6759,6761,6766,6767,6769,6774,6775,6776,6778,6799,6804,6805,6807,6811,6812,6813,6908,6909,6910,6911,6912,6913,6914,6915,6916,6917,6918,6919,6920,6921,6922,6923,6924,6925,6961,6930,6931,6933,6934,6935,6957,6936,6940,6944,6948,6937,6942,6945,6950,6938,6943,6946,6949,6939,6941,6947,6951,6926,6927,6928,6929,6952,6953,6954,6955,6956,8129,8130,7106,7107,7108,7109,7110,7226,7230,7238,7242,7246,7250,7254,9004,7229,7233,7241,7245,7249,7253,7256,9030,7227,7231,7239,7243,7247,7251,7255,9028,7228,7232,7240,7244,7248,7252,7257,9029,969,970,971,7339,7340,7341,7342,7911,8003,8004,8005,8006,8007,8008,8009,8123,8124,8125,8126,8127,8128,8131,8132,8133,8134,8136,8108,8109,8110,8111,8112,8113,8114,8116,8117,8118,8119,8120,8121,8122,8213,8219,8225,8231,8237,8243,8214,8220,8226,8232,8238,8244,8215,8221,8227,8234,8239,8245,8218,8224,8230,8235,8242,8248,8216,8222,8228,8233,8240,8246,8217,8223,8229,8236,8241,8247,8336"
            + ",8337,840,929,1680,1694,2242,2562,2666,2667,2998,2999,7265,7269,7276,7280,7288,7292,7293,7294,7297,7298,7299,7300,7304,7305,7306,7370,8102,8731,8738,8771,8772,8776,8791,8792,8793,8800,8802,8803,8994,8995,9263,9389,9391";

    private static String objectFive = "274,287,288,300,301,304,305,306,307,309,310,312,315,316,360,362,363,364,365,366,367,368,369,370,373,374,375,376,379,380,381,382,383,384,387,388,389,390,391,392,393,394,395,396,397,398,399,407,408,409,411,412,413,414,415,416,418,419,421,422,424,426,427,428,430,431,432,433,434,435,437,441,442,443,444,445,447,448,450,463,464,465,466,467,479,480,485,486,543,544,545,546,547,578,593,594,598,600,603,607,646,648,649,650,652,653,654,678,679,680,683,684,686,695,713,714,715,716,717,724,725,726,727,728,729,730,745,746,747,748,749,756,757,761,795,796,797,798,799,800,801,802,803,804,805,806,807,808,809,810,811,812,814,815,816,817,878,879,880,881,882,883,884,885,886,887,901,965,997,999,1022,1141,1142,1324,1325,1326,1327,1332,1336,1339,1344,1428,1455,1456,1457,1458,1515,1516,1517,1518,1526,1527,1528,1529,1531,1532,1533,1534,1535,1536,1537,1538,1568,1569,1570,1634,1652,1663,1664,1670,1672,1673,1675,1678,1679,1681,1685,1690,1709,1734,1736,1750,1754,1757,1759,1762,1772,1774,1776,1778,1779,1782,1784,1786,1788,1790,1794,1796,1799,1805,1807,1844,1846,1847,1849,1889,1891,1893,1894,1973,2056,2060,2150,2179,2187,2245,2249,2251,2252,2253,2254,2259,2264,2271,2273,2275,2278,2279,2281,2283,2285,2288,2290,2291,2292,2293,2300,2303,2304,2305,2306,2315,2316,2317,2318,2322,2323,2324,2325,2326,2327,2329,2331,2336,2365,2436,2451,2453,2460,2463,2464,2465,2467,2480,2483,2486,2487,2490,2495,2496,2497,2501,2502,2503,2509,2511,2512,2513,2514,2516,2554,2555,2557,2571,2573,2579,2583,2586,2588,2591,2594,2598,2599,2602,2605,2609,2610,2611,2613,2624,2642,2646,2647,2654,2659,2661,2664,2665,2673,2674,2675,3208,6671,6735,6736,6737,6739,6818,6841,6842,6843,6844,6845,6884,6897,6898,6899,6900,6902,6903,7222,7223,7224,7225,7258,7259,7260,7262,7263,7266,7267,7270,7271,7275,7279,7285,7287,7290,7301,7309,7310,7311,7312,7343,7369,7508,7509,7510,7511,7557,7651,7903,7904,7905,7906,7907,7908,7918,7924,7926,7927,8002,8050,8052,8053,8054,8055,8056,8057,8058,8059,8060,8061,8062,8063,8065,8066,8073,8082,8083,8084,8085,8087,8135,8138,8139,8141,8142,8143,8156,8157,8158,8249,8250,8251,8252,8307,8308,8309,8310,8311,8312,8313,8314,8315,8320,8342,8343,8344,8345,8346,8347,8348,8349,8350,8351,8352,8353,8354,8355,8390,8430,8435,8436,8437,8438,8439,8481,8482,8483,8484,8515,8520,8545,8546,8557,8570,8571,8621,8624,8625,8626,8680,8681,8682,8712,8729,8730,8732,8733,8734,8735,8744,8745,8746,8747,8748,8749,8750,8751,8752,8917,8971,8972,8975,8977,9077,9078,9079,9080,9081,9082,9083,9084,9085,9086,9087,9088,9247,9248,9249,9250,9251,9252,9254,9381,9382,9383,9622,9940,9941,9949,9950,9951,9952,9953,9954,9955,9999,10003,10382,10383,10384,10385,10386,10387,10388,10389,10390,10391,10392,10393,10394,10395,10396,10397,10398,10399,10400,10401,10402,10403,10404,10405,10407,7413,1635,361,10563"
            + ",291,308,313,350,438,439,446,481,487,602,651,750,842,843,844,845,846,847,848,918,1129,1610,1611,1612,1613,1614,1660,1682,1792,1801,1803,1853,1890,2241,2247,2248,2277,2287,2491,2528,2550,2551,2552,2578,2643,2644,6457,6458,6738,6740,7023,7024,7025,7026,7027,7028,7030,7032,7033,7035,7036,7059,7264,7268,7272,7273,7274,7277,7278,7281,7282,7283,7284,7291,7303,7308,7379,7380,7381,7382,7383,7384,7385,7393,7403,7404,7405,7406,7407,7408,7410,7411,7799,8159,8161,8321,8322,8323,8324,8327,8328,8356,8357,8358,8359,8360,8361,8362,8363,8369,8370,8371,8372,8373,8374,8375,8376,8385,8386,8388,8389,8391,8392,8393,8394,8397,8398,8399,8400,8401,8402,8407,8485,8486,8488,8489,8491,8492,8736,8737,8739,8740,8741,8753,8754,8755,8756,8762,8766,8767,8768,8769,8773,8774,8775,8778,8779,8780,8781,8782,8785,8799,8801,8916,9384,9385,9386,9387,9388,8335"
            + ",2411,2414,2416,2419,2422,2425,2428,2074,2409,2412,2415,2418,2421,2424,2427,2410,2413,2417,2420,2423,2426,2429,2438,2440,2441,2442,2443,2444,2445,2473,2474,2475,2476,2477,2478,2446,2447,2469,2470,2471,2472,2640,2641,2544,2545,2546,6449,7703,2530,2531,2532,7714,2485,2489,2498,6719,6720,6721,6722,6723,6724,6725,2075,2534,2535,2537,6731,6732,6750,6753,2077,6746,6748,6751,6754,6756,6758,6760,2076,6747,6749,6752,6755,6757,6759,6761,6766,6767,6769,6774,6775,6776,6778,6799,6804,6805,6807,6811,6812,6813,6908,6909,6910,6911,6912,6913,6914,6915,6916,6917,6918,6919,6920,6921,6922,6923,6924,6925,6961,6930,6931,6933,6934,6935,6957,6936,6940,6944,6948,6937,6942,6945,6950,6938,6943,6946,6949,6939,6941,6947,6951,6926,6927,6928,6929,6952,6953,6954,6955,6956,8129,8130,7106,7107,7108,7109,7110,7226,7230,7238,7242,7246,7250,7254,9004,7229,7233,7241,7245,7249,7253,7256,9030,7227,7231,7239,7243,7247,7251,7255,9028,7228,7232,7240,7244,7248,7252,7257,9029,969,970,971,7339,7340,7341,7342,7911,8003,8004,8005,8006,8007,8008,8009,8123,8124,8125,8126,8127,8128,8131,8132,8133,8134,8136,8108,8109,8110,8111,8112,8113,8114,8116,8117,8118,8119,8120,8121,8122,8213,8219,8225,8231,8237,8243,8214,8220,8226,8232,8238,8244,8215,8221,8227,8234,8239,8245,8218,8224,8230,8235,8242,8248,8216,8222,8228,8233,8240,8246,8217,8223,8229,8236,8241,8247,8336"
            + ",8337,840,929,1680,1694,2242,2562,2666,2667,2998,2999,7265,7269,7276,7280,7288,7292,7293,7294,7297,7298,7299,7300,7304,7305,7306,7370,8102,8731,8738,8771,8772,8776,8791,8792,8793,8800,8802,8803,8994,8995,9263,9389,9391"
            + ",9277,1892,1691,1696,2580,581,8339";

    public static void getRandomObjectOne(Player player) {
        int value = Formulas.getRandomValue(0, 100000);
        int template = -1;
        if (value >= 0 && value < 5000) {//Guildalogemme
            template = 1575;
        } else if (value >= 5000 && value < 10000) {//Manuel du tailleur
            template = 966;
        } else if (value >= 10000 && value < 100000) {//All objects
            template = Integer.parseInt(objectOne.split(",")[Formulas.getRandomValue(0, objectOne.split(",").length - 1)]);
        }
        GameObject obj = World.world.getObjTemplate(template).createNewItem(1, false, 0);
        if (player.addObjet(obj, true))
            World.world.addGameObject(obj, true);
        SocketManager.GAME_SEND_Ow_PACKET(player);
        SocketManager.GAME_SEND_Im_PACKET(player, "021;" + 1 + "~" + template);
    }

    public static void getRandomObjectTwo(Player player) {
        int value = Formulas.getRandomValue(0, 100000);
        int template = -1;
        if (value >= 0 && value < 5000) {//Guildalogemme
            template = 1575;
        } else if (value >= 5000 && value < 10000) {//Manuel du tailleur
            template = 966;
        } else if (value >= 10000 && value < 100000) {//All objects
            template = Integer.parseInt(objectTwo.split(",")[Formulas.getRandomValue(0, objectTwo.split(",").length - 1)]);
        }
        GameObject obj = World.world.getObjTemplate(template).createNewItem(1, false, 0);
        if (player.addObjet(obj, true))
            World.world.addGameObject(obj, true);
        SocketManager.GAME_SEND_Ow_PACKET(player);
        SocketManager.GAME_SEND_Im_PACKET(player, "021;" + 1 + "~" + template);
    }

    public static void getRandomObjectTree(Player player) {
        int value = Formulas.getRandomValue(0, 100000);
        int template = -1;
        if (value >= 0 && value < 5000) {//Guildalogemme
            template = 1575;
        } else if (value >= 5000 && value < 10000) {//Manuel du tailleur
            template = 966;
        } else if (value >= 10000 && value < 100000) {//All objects
            template = Integer.parseInt(objectTree.split(",")[Formulas.getRandomValue(0, objectTree.split(",").length - 1)]);
        }
        GameObject obj = World.world.getObjTemplate(template).createNewItem(1, false, 0);
        if (player.addObjet(obj, true))
            World.world.addGameObject(obj, true);
        SocketManager.GAME_SEND_Ow_PACKET(player);
        SocketManager.GAME_SEND_Im_PACKET(player, "021;" + 1 + "~" + template);
    }

    public static void getRandomObjectFour(Player player) {
        int value = Formulas.getRandomValue(0, 100000);
        int template = -1;
        if (value >= 0 && value < 5000) {//Guildalogemme
            template = 1575;
        } else if (value >= 5000 && value < 10000) {//Manuel du tailleur
            template = 966;
        } else if (value >= 10000 && value < 100000) {//All objects
            template = Integer.parseInt(objectFour.split(",")[Formulas.getRandomValue(0, objectFour.split(",").length - 1)]);
        }
        GameObject obj = World.world.getObjTemplate(template).createNewItem(1, false, 0);
        if (player.addObjet(obj, true))
            World.world.addGameObject(obj, true);
        SocketManager.GAME_SEND_Ow_PACKET(player);
        SocketManager.GAME_SEND_Im_PACKET(player, "021;" + 1 + "~" + template);
    }

    public static void getRandomObjectFive(Player player) {
        int value = Formulas.getRandomValue(0, 100000);
        int template = -1;
        if (value >= 0 && value < 5000) {//Guildalogemme
            template = 1575;
        } else if (value >= 5000 && value < 10000) {//Manuel du tailleur
            template = 966;
        } else if (value >= 10000 && value < 100000) {//All objects
            template = Integer.parseInt(objectFive.split(",")[Formulas.getRandomValue(0, objectFive.split(",").length - 1)]);
        }
        GameObject obj = World.world.getObjTemplate(template).createNewItem(1, false, 0);
        if (player.addObjet(obj, true))
            World.world.addGameObject(obj, true);
        SocketManager.GAME_SEND_Ow_PACKET(player);
        SocketManager.GAME_SEND_Im_PACKET(player, "021;" + 1 + "~" + template);
    }
}