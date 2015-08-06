package knapsack;

import java.util.Scanner;

/*
 * 0-1背包问题,wi是正整数 
 * 
 * 
 */
public class Soultion {
	public static int knapsack(int []v,int []w,int c,int [][]m){
		int n=v.length-1;
		int jMax=Math.min(w[n]-1, c);
		//初始化
		for(int j=0;j<=jMax;j++){
			m[n][j]=0;
		}
		for(int j=w[n];j<=c;j++){
			m[n][j]=v[n];
		}
		//starting....
		for(int i=n-1;i>1;i--){
			jMax=Math.min(w[i]-1, c);
			for(int j=0;j<=jMax;j++){
				m[i][j]=m[i+1][j];
			}
			for(int j=w[i];j<=c;j++){
				m[i][j]=Math.max(m[i+1][j], m[i+1][j-w[i]]+v[i]);
			}
			
		}
		m[1][c]=m[2][c];
		if(c>w[1])
		m[1][c]=Math.max(m[2][c],m[2][c-w[1]]+v[1]);
		return m[1][c];
			
	}
	public static void traceback(int m[][],int x[],int c,int w[]){
		int n=w.length-1;
		for(int i=1;i<n;i++){
			if(m[i][c]==m[i+1][c]){
				x[i]=0;
			}else{
				x[i]=1;
				c-=w[i];
			}
		}
		x[n]=m[n][c]>0?1:0;
	}
	public static void main(String[] args){
		System.out.println("请输入数组的长度 :");
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		System.out.println("请输入w数组");
		int w[]=new int[n+1];
		for(int i=1;i<=n;i++)
			w[i]=sc.nextInt();
		System.out.println("请输入v数组");
		int v[]=new int[n+1];
		for(int i=1;i<=n;i++)
			v[i]=sc.nextInt();
		System.out.println("请输入背包的大小：");
		int c=sc.nextInt();
		int m[][]=new int[n+1][c+1];
		System.out.println(knapsack(v, w, c, m));
		int x[]=new int[n+1];
		traceback(m, x, c, w);
		for(int i=1;i<=n;i++)
			System.out.print(x[i]);
	}
}
