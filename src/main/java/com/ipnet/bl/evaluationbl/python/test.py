import tensorflow as tf
import trigram
from SmartEvaluationDao import saveEvaluation

with tf.Session() as sess:
    filePath = '/Users/panxy/IdeaProjects/IPNET/src/main/java/com/ipnet/bl/evaluationbl/python/'
    # sess.run(tf.global_variables_initializer())
    new_saver = tf.train.import_meta_graph(filePath + 'model.cpkt.meta')
    new_saver.restore(sess, filePath + 'model.cpkt')
    graph = tf.get_default_graph()
    query_batch = graph.get_operation_by_name("QueryBatch")
    input = graph.get_tensor_by_name("QueryBatch:0")
    query_l4 = graph.get_operation_by_name("Prediction")
    prediction = graph.get_tensor_by_name("Prediction:0")
    value = sess.run(prediction, feed_dict={input: trigram.test_set_x})
    print(value)
    saveEvaluation(value)
