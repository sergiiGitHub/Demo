from flask import Flask, render_template
app = Flask(__name__)

@app.route('/')
def home():
    return render_template('home.html')

@app.route('/hello', methods=['GET', 'POST'])
def cool_form():
    return render_template('hello.html')
    
if __name__ == '__main__':
    app.run(debug=True)
    

