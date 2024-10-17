#include <iostream>
using namespace std;
int function(int n,int c){
    if (c==n)
        return n;
    else 
        {
        return c*(1+ function(n,c+1));
        }
}
int main(){
    int n;
    cout<<"please enter the number n:";
    cin>>n;
    cout<<function(n,1);
}