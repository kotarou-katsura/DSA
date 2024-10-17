#include <iostream>
using namespace std;
int multiplication(int a,int b,int r){
    if (b==0)
        return r;
    else 
        return multiplication(a,b-1,a+r);
}
int main(){
    int a;
    int b;
    cout<<"please enter two numbers:";
    cin>>a>>b;
    cout<<multiplication(a,b,0);
}