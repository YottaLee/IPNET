# -*- coding: utf-8 -*-
import collections
from collections import Counter
import tensorflow as tf
import numpy as np


def get_token_dict(text):
    token_list = list()
    token_list.append('#' + text[0:2])
    for i in range(len(text) - 2):
        token_list.append(text[i: i + 3])
    token_list.append(text[len(text) - 2:] + '#')
    return token_list


def get_token_dict_uni_gram(text):
    token_list = list()
    # token_list.append('#' + text[0:2])
    for i in range(len(text)):
        token_list.append(text[i])
    # token_list.append(text[len(text) - 2:] + '#')
    return token_list


def build_dataset(token_list, num_tokens):
    """Process raw inputs into a dataset."""
    count = [['UNK', -1]]
    count.extend(collections.Counter(token_list).most_common(num_tokens - 1))
    # print(len(count))
    dictionary = dict()
    for word, _ in count:
        dictionary[word] = len(dictionary)
    data = list()
    unk_count = 0
    for word in token_list:
        if word in dictionary:
            index = dictionary[word]
        else:
            index = 0  # dictionary['UNK']
            unk_count += 1
        data.append(index)
    count[0][1] = unk_count
    reversed_dictionary = dict(zip(dictionary.values(), dictionary.keys()))
    return data, count, dictionary, reversed_dictionary


str1 = '用于果蔬分选的输送托盘回收装置'
str2 = '农产品内部品质检测过程中的目标跟踪装置'
str3 = '基于近红外/可见光的水果品质快速无损在线检测系统'
str4 = '一种基于机器视觉的苹果表面无冗余信息图像处理方法'
str5 = '带称重功能的小型球形水果糖酸比快速无损检测装置'
str6 = '用于球状水果无冗余图像信息获取的方法和装置'
str7 = '基于开关电源的光源电压控制装置'
str8 = '基于可见近红外光谱的柑橘类水果内部品质在线检测装置'
str9 = '搓丝机油路分离回收装置'
str10 = '搓丝机滑块安装结构'
str11 = '适用于搓丝机送料闸刀的导向结构'
str12 = '家用干豆角加工装置的豇豆烘干机构'
str13 = '家用干豆角加工装置的热风引导机构'
str14 = '一种液压驱动的林业用伐木锯装置及其使用方法'
str15 = '带毛刷的市政桥梁护栏清洗装置及其使用方法'
str16 = '一种使用清扫毛刷的肥料颗粒干燥装置'
str17 = '一种蓝莓海带复合饮品的制备方法'
str18 = '固定式智能化蚊虫捕杀系统'
str19 = '充气式淋浴房'
str20 = '充气型淋浴房'
str21 = '多功能智能清洁机器人装置'
tokens = get_token_dict_uni_gram(str1)
tokens += get_token_dict_uni_gram(str2)
tokens += get_token_dict_uni_gram(str3)
tokens += get_token_dict_uni_gram(str4)
tokens += get_token_dict_uni_gram(str5)
tokens += get_token_dict_uni_gram(str6)
tokens += get_token_dict_uni_gram(str7)
tokens += get_token_dict_uni_gram(str8)
tokens += get_token_dict_uni_gram(str9)
tokens += get_token_dict_uni_gram(str10)
tokens += get_token_dict_uni_gram(str11)
tokens += get_token_dict_uni_gram(str12)
tokens += get_token_dict_uni_gram(str13)
tokens += get_token_dict_uni_gram(str14)
tokens += get_token_dict_uni_gram(str15)
tokens += get_token_dict_uni_gram(str16)
tokens += get_token_dict_uni_gram(str17)
tokens += get_token_dict_uni_gram(str18)
tokens += get_token_dict_uni_gram(str19)
tokens += get_token_dict_uni_gram(str20)
tokens += get_token_dict_uni_gram(str21)

training_label, count, dicts, words = build_dataset(tokens, len(tokens))
TRIGRAM_D = len(dicts)


def get_vec(str):
    vec_dict = dict()
    for token in dicts:
        vec_dict[token] = 0
    vec_tokens = get_token_dict(str)
    for vec_token in vec_tokens:
        vec_dict[vec_token] += 1
    return list(vec_dict.values())


def get_vec_uni_gram(str):
    vec_dict = dict()
    for token in dicts:
        vec_dict[token] = 0
    vec_tokens = get_token_dict_uni_gram(str)
    for vec_token in vec_tokens:
        vec_dict[vec_token] += 1
    return list(vec_dict.values())


test_str1 = get_vec_uni_gram(str1)
test_str2 = get_vec_uni_gram(str2)
test_str3 = get_vec_uni_gram(str3)
test_str4 = get_vec_uni_gram(str4)
test_str5 = get_vec_uni_gram(str5)
test_str6 = get_vec_uni_gram(str6)
test_str7 = get_vec_uni_gram(str7)
test_str8 = get_vec_uni_gram(str8)
test_str9 = get_vec_uni_gram(str9)
test_str10 = get_vec_uni_gram(str10)
test_str11 = get_vec_uni_gram(str11)
test_str12 = get_vec_uni_gram(str12)
test_str13 = get_vec_uni_gram(str13)
test_str14 = get_vec_uni_gram(str14)
test_str15 = get_vec_uni_gram(str15)
test_str16 = get_vec_uni_gram(str16)
test_str17 = get_vec_uni_gram(str17)
test_str18 = get_vec_uni_gram(str18)
test_str19 = get_vec_uni_gram(str19)
test_str20 = get_vec_uni_gram(str20)
test_str21 = get_vec_uni_gram(str21)

p1 = [100.0]
p2 = [100.0]
p3 = [100.0]
p4 = [100.0]
p5 = [100.0]
p6 = [100.0]
p7 = [100.0]
p8 = [100.0]
p9 = [1.5]
p10 = [1.5]
p11 = [1.5]
p12 = [1.5]
p13 = [1.5]
p14 = [1.8]
p15 = [1.8]
p16 = [1.5]
p17 = [1.8]
p18 = [2.5]
p19 = [2.0]
p20 = [2.0]
p21 = [2.9]

index1 = ['201310242853.1']
index2 = ['201310125308.4']
index3 = ['201210459001.3']
index4 = ['201310124250.1']
index5 = ['201210419668.0']
index6 = ['201310125257.5']
index7 = ['201210260002.5']
index8 = ['201520237555.8']
index9 = ['201510200654.3']
index10 = ['201410355679.6']
index11 = ['201510200508.0']
index12 = ['201410712203.3']
index13 = ['201410713620.X']
index14 = ['201510077533.4']
index15 = ['201510131110.6']
index16 = ['201510156738.1']
index17 = ['201410771171.4']
index18 = ['201510535356.X']
index19 = ['201410314648.6']
index20 = ['201410315315.5']
index21 = ['201310140223.3']

trade_type1 = ['转让']
trade_type2 = ['转让']
trade_type3 = ['转让']
trade_type4 = ['转让']
trade_type5 = ['转让']
trade_type6 = ['转让']
trade_type7 = ['转让']
trade_type8 = ['转让']
trade_type9 = ['许可']
trade_type10 = ['许可']
trade_type11 = ['许可']
trade_type12 = ['许可']
trade_type13 = ['许可']
trade_type14 = ['许可']
trade_type15 = ['许可']
trade_type16 = ['许可']
trade_type17 = ['转让']
trade_type18 = ['转让']
trade_type19 = ['转让']
trade_type20 = ['转让']
trade_type21 = ['转让']

time1 = ['2016/7/21']
time2 = ['2016/7/21']
time3 = ['2016/7/21']
time4 = ['2016/7/21']
time5 = ['2016/7/21']
time6 = ['2016/7/21']
time7 = ['2016/7/21']
time8 = ['2016/7/21']
time9 = ['2017/8/11']
time10 = ['2017/8/11']
time11 = ['2017/8/11']
time12 = ['2017/8/11']
time13 = ['2017/8/11']
time14 = ['2017/8/11']
time15 = ['2017/8/11']
time16 = ['2017/8/11']
time17 = ['2017/8/11']
time18 = ['2017/9/13']
time19 = ['2017/8/11']
time20 = ['2017/8/11']
time21 = ['2017/5/9']

price_type1 = ['成交价']
price_type2 = ['成交价']
price_type3 = ['成交价']
price_type4 = ['成交价']
price_type5 = ['成交价']
price_type6 = ['成交价']
price_type7 = ['成交价']
price_type8 = ['成交价']
price_type9 = ['卖方报价']
price_type10 = ['卖方报价']
price_type11 = ['卖方报价']
price_type12 = ['卖方报价']
price_type13 = ['卖方报价']
price_type14 = ['卖方报价']
price_type15 = ['卖方报价']
price_type16 = ['卖方报价']
price_type17 = ['卖方报价']
price_type18 = ['卖方报价']
price_type19 = ['卖方报价']
price_type20 = ['卖方报价']
price_type21 = ['卖方报价']

training_set_x = [test_str1,
                  test_str2,
                  # test_str3,
                  test_str4,
                  test_str5,
                  test_str6,
                  test_str7,
                  test_str8,
                  test_str9,
                  test_str10,
                  test_str11,
                  test_str12,
                  test_str13,
                  test_str14,
                  test_str15,
                  test_str16,
                  test_str17,
                  test_str18,
                  test_str19,
                  test_str20,
                  test_str21]
training_set_y = [p1,
                  p2,
                  # p3,
                  p4,
                  p5,
                  p6,
                  p7,
                  p8,
                  p9,
                  p10,
                  p11,
                  p12,
                  p13,
                  p14,
                  p15,
                  p16,
                  p17,
                  p18,
                  p19,
                  p20,
                  p21]
test_set_x = [test_str3]
test_set_y = [p3]
