#include <iostream>
using namespace std;

int gcd(int a, int b) {
    if (b == 0)
        return a;
    else
        return gcd(b, a % b);
}

int main() {
    int a;
    int b;
    cout<<"please enter two numbers:";
    cin>>a>>b;
    cout <<gcd(a,b);
}
