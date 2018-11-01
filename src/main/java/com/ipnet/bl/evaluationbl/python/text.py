# -*- coding: UTF-8 -*-
import tensorflow as tf
import pandas as pd
import pickle
from sys import argv
import numpy as np
import matplotlib as plt
from SmartEvaluationDao import saveEvaluation

path = "/Users/panxy/IdeaProjects/IPNET/src/main/java/com/ipnet/bl/evaluationbl/python/"


def get_token_dict_uni_gram(text):
    token_list = list()
    # token_list.append('#' + text[0:2])
    for i in range(len(text)):
        token_list.append(text[i])
    # token_list.append(text[len(text) - 2:] + '#')
    return token_list


def get_vec_uni_gram(str):
    vec_dict = dict()
    for token in dicts:
        vec_dict[token] = 0
    vec_tokens = get_token_dict_uni_gram(str)
    for vec_token in vec_tokens:
        if vec_token in vec_dict:
            vec_dict[vec_token] += 1
        else:
            vec_dict['UNK'] += 1
    return list(vec_dict.values())


df_new = pd.read_pickle(path + 'df.pkl')
pkl_file = open(path + 'myfile.pkl', 'rb')
dicts = pickle.load(pkl_file)
TRIGRAM_D = len(dicts)
pkl_file.close()


def return_price(text):
    vec_list = list()
    vec_list.append(get_vec_uni_gram("text"))
    price = 0.0
    with tf.Session() as sess:
        # sess.run(tf.global_variables_initializer())
        new_saver = tf.train.import_meta_graph(path + 'model.cpkt.meta')
        new_saver.restore(sess, path + 'model.cpkt')
        graph = tf.get_default_graph()
        query_batch = graph.get_operation_by_name("QueryBatch")
        input = graph.get_tensor_by_name("QueryBatch:0")
        query_l4 = graph.get_operation_by_name("Prediction")
        prediction = graph.get_tensor_by_name("Prediction:0")
        price = sess.run(prediction, feed_dict={input: vec_list})
    return price


def get_price_by_name(name):
    text = df_new[df_new['name_list'] == name]['content_list']
    price = return_price(text)[0][0]
    print(price)
    saveEvaluation(name,price)
    return price


# get_price_by_name(argv[1])
get_price_by_name("一种LED球泡灯")
