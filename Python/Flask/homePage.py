#reference https://www.tutorialspoint.com/flask/index.htm
from flask import Flask, render_template, request, redirect, url_for
app = Flask(__name__)

@app.route('/', methods=['GET', 'POST'])
def home():
    return render_template('home.html')
    
@app.route('/hello', methods=['GET', 'POST'])
def hello():
    if request.method == 'POST':
        print "post"
    return render_template('hello.html')

@app.route('/success/<name>')
def success(name):
   return 'welcome %s' % name

@app.route('/currency/<name>')
def currency(name):
   return 'you are in currency %s' % name

@app.route('/login',methods = ['POST', 'GET'])
def login():
   if request.method == 'POST':
      user = request.form['nm']
      #here your code
      currency = request.form['currency']
      if (currency == "usd"):
          return redirect(url_for('currency',name = currency))        
      
      return redirect(url_for('success',name = user))
   else:
      user = request.args.get('nm')
      return redirect(url_for('success',name = user))
    
if __name__ == '__main__':
    app.run(debug=True)
    

