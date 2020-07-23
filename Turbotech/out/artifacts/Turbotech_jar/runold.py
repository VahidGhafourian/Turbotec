import pickle
import numpy as np
import sched, time
import sys
sys.path.append('/home/anaconda3/lib/python3.7/site-packages')

def load(filename):
    with open(filename, 'rb') as file:
        return pickle.load(file)


class NextData():
    def __init__(self,x_test):
        self.x_test = x_test
        self.i = 0
    def __call__(self):
         try:
            output = self.x_test[self.i]
            self.i = self.i+1
            return output
         except:
             print('no more data!')
             exit()
        
def predict(clf, x_in, prefix = ''):
    proba = 100*clf.predict_proba([x_in])

    f = open('temp','r')
    rep = f.read()
    f.close()

    for i in range(8):
        rep = rep.replace('$S'+str(i+1),'{:2.2f}'.format(x_in[i]))


    for i in range(13,-1,-1):
        rep = rep.replace('$P'+str(i+1),'{:2.0f}'.format(proba[0,i]))

    rep = rep.replace('$TIMEINTERVAL',str(dtime))

    f = open(prefix+'DataFile','w')
    f.write(rep)
    f.close()

def run(clf): 
    x_in = next_data()
    print(next_data.i)
    predict(clf, x_in, prefix = '')
    s.enter(dtime/1000, 1, run, (clf,))

    
data = np.load('dataset.npz')
x = data['x']
y = data['y']
next_data = NextData(x)

dtime = 2000
clf = load('model1')
s = sched.scheduler(time.time, time.sleep)
s.enter(dtime/1000, 1, run, (clf,))
s.run()
