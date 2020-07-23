import psycopg2
from config import config
import pickle
import numpy as np

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

def defineTabel(data, conn, cur):
    cur.execute("Create Table Turbine( id INT, sensor1 float(24), sensor2 float(24), sensor3 float(24), sensor4 float(24), sensor5 float(24), sensor6 float(24), sensor7 float(24), temp float(24));")
    conn.commit()
    for i, row in enumerate(data):
        cur.execute("INSERT INTO Turbine VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s);",(i, row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7]))
        conn.commit()

def connect(rowid):
    """ Connect to the PostgreSQL database server """
    conn = None
    try:
        # read connection parameters
        params = config()

        # connect to the PostgreSQL server
        #print('Connecting to the PostgreSQL database...')
        conn = psycopg2.connect(**params)
		
        # create a cursor
        cur = conn.cursor()

        #run for first time:
        #defineTabel(data, conn, cur)

	    # execute a statement
        #conn.commit()
        a = str(rowid)
        cur.execute("SELECT * FROM Turbine where id="+ a +";")
        result = cur.fetchone()
        print(result)
	    # close the communication with the PostgreSQL
        cur.close()
        return result
    except (Exception, psycopg2.DatabaseError) as error:
        print(error)
    finally:
        if conn is not None:
            conn.close()
            print('Database connection closed.')

