#include <iostream>
#include <string>
using namespace std;
bool is_duplicate(const std::string& s) {
    int n = s.length();
    if (n == 0)
        return true;
    for (int i = 1; i <= n / 2; ++i) {
        string s1 = s.substr(0, i);
        string repeated_s1;
        for (int j = 0; j < n / i; ++j) {
            repeated_s1 += s1;
        }
        if (repeated_s1 == s) 
            if (is_duplicate(s1.substr(1)))   
                return true;
    }
    return false;
}

int main(){
    // cout << is_duplicate("htmmtmmhtmmtmm");
    // cout << is_duplicate("hpopohpopo");
    cout<<"Enter the string:";
    string str;
    cin>>str;
    int k = is_duplicate(str);
    if (k)
        cout<<"The string is a duplicate string";
    else
        cout<<"The string is not a duplicate string";
}