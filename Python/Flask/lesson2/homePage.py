#reference https://www.tutorialspoint.com/flask/index.html
from flask import Flask, render_template, request, redirect, url_for, jsonify
app = Flask(__name__)

@app.route('/', methods=['GET', 'POST'])
def home():
    return render_template('homeM.html')

@app.route('/currencyUSD/<name>')
def currencyUSD(name):
    print ("name:", name)    
    r1 = eval(name)
    print ("result:", r1) 
    return render_template('hello.html', result = r1)
   
@app.route('/currency/<name>')
def currency(name):
   return 'you are in currency %s' % name

@app.route('/login',methods = ['POST', 'GET'])
def login():
   if request.method == 'POST':
      #currency = request.form['value']
      currency = str(request.form['currency'])
      value = int(request.form['value'])
      print("currency:", currency, "; value: ", value) 
      if (currency == "USD"):
          dict = {'currency':currency,'value':value}
          print ("data:", dict)
          return redirect(url_for('currencyUSD',name = dict))
      elif (currency == "EUR"):
          return redirect(url_for('currency',name = currency))
      
      
   return 'Incorrect arg, add logic in py'
    
if __name__ == '__main__':
    app.run(debug=True)
    

