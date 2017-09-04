#include <iostream>
#include <string>
#include <vector>

//http://practice.geeksforgeeks.org/problems/karatsuba-algorithm/0
//http://www.geeksforgeeks.org/divide-and-conquer-set-2-karatsuba-algorithm-for-fast-multiplication/

using namespace std;

int convert(char ch){
    return ch - '0';
}

char convert(bool b){
    return b ? '1' : '0';
}

string convertToString(int i){
    string res;

    while(i > 0 ){
        res = (char((i & 1) + '0')) + res;
        i = i >> 1;
    }
    return res;
}

int convertToInt(string str){
    int res = 0;

    for(int i = 0; i < str.length(); ++i){
        if (str.at(i) == '1'){
            res += (1 << (str.length() - 1 - i));
        }
    }
    return res;
}

void appfront(string &left, const int& length){
    for ( int i = left.length(); i < length; ++i ){
        left = '0' + left;
    }
}

void makeSameLength(string& left, string& right){
    int length = left.length();
    if(right.length() < length){
        appfront(right, length);
    } else if (length < right.length()){ 
        length = right.length();
        appfront(left, length);
    }
}

string add(string left, string right ){

    string res;
    bool c = 0;

    makeSameLength(left, right);

    for(int i = left.length() - 1; i >= 0; --i){
        bool f = convert(left.at(i));
        bool s = convert(right.at(i));

        bool r = f ^ s ^ c;
        res = convert(r) + res;
        c = (f & c) | (s & c) | (f & s);
    }

    if (c){
        res = '1' + res;
    }
    return res;
}

string subtrack(string left, string right ){

    makeSameLength(left, right);

    for(int i = left.length() - 1; i >= 0; --i){
        bool f = convert(left.at(i));
        bool s = convert(right.at(i));
        bool r = (f^s);
        
        left.at(i) = (f^s) ? '1' : '0';
        if (r & !f){
            int j = i - 1;
            while(left.at(j) == '0' && j >= 0){
                left.at(j) = '1';
                --j;
            }
            left.at(j) = '0';
        }
    }

    return left;
}

int multiplyInt(string left, string right){

    makeSameLength(left, right);
    int length = left.length();

    if (length == 0){
        return 0;
    } 
    if (length == 1){
        return convert(left.at(0)) * convert(right.at(0));
    }

    int fh = length / 2;
    int sh = length - fh;

    string Xl = left.substr(0, fh);
    string Xr = left.substr(fh, sh);

    string Yl = right.substr(0, fh);
    string Yr = right.substr(fh, sh); 

    int P1 = multiplyInt(Xl, Yl);
    int P2 = multiplyInt(Xr, Yr);
    int P3 = multiplyInt(add(Xl, Xr), add(Yl, Yr));

    return P1 * (1 << (sh << 1))  + (1 << sh) * ( P3 - P1 - P2) + P2;
}

void trim(string &left, string &right){

    while(!left.empty() && left.at(0) == '0'){
        left = left.substr(1, left.length());
    }
    while(!right.empty() && right.at(0) == '0'){
        right = right.substr(1, right.length());
    }
}

// Multiplies str1 and str2, and prints result.
string multiply(string num1, string num2)
{
    int n1 = num1.size();
    int n2 = num2.size();
    if (n1 == 0 || n2 == 0)
       return "0";
 
    // will keep the result number in vector
    // in reverse order
    vector<int> result(n1 + n2, 0);
 
    // Below two indexes are used to find positions
    // in result. 
    int i_n1 = 0; 
    int i_n2 = 0; 
 
    // Go from right to left in num1
    for (int i=n1-1; i>=0; i--)
    {
        int carry = 0;
        int n1 = num1[i] - '0';
 
        // To shift position to left after every
        // multiplication of a digit in num2
        i_n2 = 0; 
         
        // Go from right to left in num2             
        for (int j=n2-1; j>=0; j--)
        {
            // Take current digit of second number
            int n2 = num2[j] - '0';
 
            // Multiply with current digit of first number
            // and add result to previously stored result
            // at current position. 
            int sum = n1*n2 + result[i_n1 + i_n2] + carry;
 
            // Carry for next iteration
            carry = sum/10;
 
            // Store result
            result[i_n1 + i_n2] = sum % 10;
 
            i_n2++;
        }
 
        // store carry in next cell
        if (carry > 0)
            result[i_n1 + i_n2] += carry;
 
        // To shift position to left after every
        // multiplication of a digit in num1.
        i_n1++;
    }
 
    // ignore '0's from the right
    int i = result.size() - 1;
    while (i>=0 && result[i] == 0)
       i--;
 
    // If all were '0's - means either both or
    // one of num1 or num2 were '0'
    if (i == -1)
       return "0";
 
    // generate the result string
    string s = "";
    while (i >= 0)
        s += std::to_string(result[i--]);
 
    return s;
}


string multiplyHex(string num1, string num2){
    return NULL;
}

void testAdd(){
    string first[] =  {  "1", "11",  "101",  "1100", "1010" };
    string second[] = {  "01", "11",  "111",  "1101", "101" };
    string expected[] = { "10", "110", "1100", "11001", "1111" };


    for( int i = 0; i < 5; ++i ){
        string actual = add(first[i], second[i]);

        if (!(expected[i] == actual)){
            cout << "testAdd: fail: i: " << i << endl;
            return;
        }
    }

    string actual = add("111", "101");
    if (("1111" == actual)){
        cout << "testAdd: fail: not equal " << endl;
        return;
    }

    cout << "testAdd: path" << endl;
}

void testMulti(){
    string first[] =  {   "1", "2",  "12",    "24"};
    string second[] = {   "2", "3",  "13",  "789"};
    string expected[] = { "2", "6", "156", "18936"};

    for( int i = 0; i < 4; ++i ){
        string actual = multiply(first[i], second[i]);

        if (!(expected[i] == actual)){
            cout << "testMulti: fail: i: " << i << "; a: " << actual << "; e:" << expected[i] << endl;
            return;
        }
    }


    string actual = multiply("1111", "101");
    if (("11001" == actual)){
        cout << "testMulti: fail: not equal " << endl;
        return;
    }

    cout << "testMulti: path" << endl;
}

void testMultiInt(){
    string first[] =  {  "10",  "101",  "1100", "1010" };
    string second[] = {  "11",  "111",  "1101", "101" };
    int expected[] = {6, 35, 156, 50};

    for( int i = 0; i < 4; ++i ){
        int actual = multiplyInt(first[i], second[i]);

        if (!(expected[i] == actual)){
            cout << "testMultiInt: fail: i: " << i << "; a: " << actual << "; e:" << expected[i] << endl;
            return;
        }
    }


    string actual = multiply("1111", "101");
    if (("11001" == actual)){
        cout << "testMultiInt: fail: not equal " << endl;
        return;
    }

    cout << "testMultiInt: path" << endl;
}

void testConvertToBin(){
    string expected[] =  {  "0",  "1",  "10", "11", "100", "101" };

    for( int i = 1; i < 6; ++i ){
        string actual = convertToString(i);

        if (!(expected[i] == actual)){
            cout << "testConvertToBin: fail: i: " << i << "; a: " << actual << "; e:" << expected[i] << endl;
            return;
        }
    }

    cout << "testConvertToBin: path" << endl;
}

void testSubtraction(){
    string first[] =  {  "11", "101", "1101", "100" };
    string second[] = {  "11",  "11", "1100", "01" };
    string expected[] = {"00", "010", "0001", "011" };


    for( int i = 0; i < 4; ++i ){
        string actual = subtrack(first[i], second[i]);

        if (!(expected[i] == actual)){
            cout << "fail: i: " << i << endl;
            return;
        }
    }

    string actual = add("111", "101");
    if (("001" == actual)){
        cout << "testSubtraction: fail: not equal " << endl;
        return;
    }

    cout << "testSubtraction: path" << endl;
}

void testConvertInt(){
    string input[] = {"10", "101", "111"};
    int expected[] = {2, 5, 7};

    for ( int i = 0; i < 3; ++i ){
        if (expected[i] != convertToInt(input[i]) ){
           cout << "testConvertInt: fail: " <<input[i] << " to " << expected[i] << endl;
           return;
        }
    }
    
    cout << "testConvertInt: path" << endl;
}

void testMultiplyHexadecimal(){

    string first[] =  {  "1", "2",  "12", "15"};
    string second[] = {  "2", "3",  "24",  "7"};
    string expected[] = {"2", "6", "288", "93"};

    for( int i = 0; i < 4; ++i ){
        string actual = multiplyHex(first[i], second[i]);

        if (!(expected[i] == actual)){
            cout << "testMulti: fail: i: " << i << "; a: " << actual << "; e:" << expected[i] << endl;
            return;
        }
    }

}

bool test(){
    bool isTest = true;
    if (!isTest){
        return isTest;
    }
    //testAdd();
    //testSubtraction();
    //testMultiInt();
    //testMulti();
    //testConvertToBin();
    //testConvertInt();
    testMultiplyHexadecimal();
    return isTest;
}

int main(){

    freopen("input.txt", "r", stdin);

    int t;
    string left, right;

    if (test()){
        return 0;
    }
    cin >> t;

    while(t > 0){
        cin >> left >> right;
        cout << multiply(left, right) << endl;
        --t;
    }

    return 0;
}
