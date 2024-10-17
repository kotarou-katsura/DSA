#include <iostream>
using namespace std;
int division(int a,int b,int r){
    if (a<b)
        return r;
    else 
        return division(a-b,b,r+1);
}
int main(){
    int a;
    int b;
    cout<<"please enter two namber a and b :";
    cin>>a>>b;
    cout<<division(a,b,0);
}