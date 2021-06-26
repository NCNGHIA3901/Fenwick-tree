package BIT;

import java.util.Scanner;
import java.util.Random;

public class BIT_CONSOLE {

    static Scanner sc = new Scanner(System.in);
    static int Max = 200; // có thể gán lớn hơn
    static int N, in; // số phần tử
    static int BITree[] = new int[Max];
    static int Sum_A[] = new int[Max];
    static int a[] = new int[Max]; 
    static Random rd = new Random();

    public static void main(String[] args) {
        int x, y, z;
        int input;
        while(true){
            System.out.println("1.Nhập Node Thủ công"
                + "\n2.Nhập N node"
                + "\n3.Nhập Random N node"
                + "\n4.Thoát");
            System.out.print("Chọn thao tác: ");  input = sc.nextInt();
            sc.skip("\n");
            if(input == 1){
                System.out.print("Nhâp Node: ");
                String s = sc.nextLine();
                s = s.trim();
                String c[] = s.split(" ");
                for (int i = 0, j = 1; i < c.length; i++, j++){
                    a[j] = Integer.parseInt(c[i]);
                }
                N = c.length;
            }
            else{
                if(input == 2){
                    System.out.print("Nhập N: "); int n = sc.nextInt();
                    for (int i = 1; i <= n; i++){
                        a[i] = i;
                        N = i;
                    }
                }
                else{
                    if(input == 3){
                        N = rd.nextInt(20);
                        for (int i = 1; i <= N; i++){
                            a[i] = rd.nextInt(20);
                        }
                    }
                    else{
                        if(input == 4) break;
                        else System.out.println("không có chức năng này");
                    }
                }
            }
            create_tree(); // cập nhật giá trị mảng vào giá trị cây
	    show();
            //-----------------------------------------------------

            System.out.println("1. Thực hiện thao tác khác");
            System.out.println("2. Tạo cây mới");
            System.out.println("3. Thoát");
            System.out.print("Nhập thao tác: "); int check = sc.nextInt();
            if(check == 1) {
            System.out.println("1. Update tại vị trí bất kỳ");
            System.out.println("2. Update trong đoạn bất kỳ");
            System.out.println("3. Update all");
            System.out.println("4. Tính sum 1-n");
            System.out.println("5. Tính sum trong đoạn bất kỳ");
            System.out.println("6. EXIT");
            System.out.print("Chọn thao tác: ");
                    //------------------------------------------------

            String act;
                while(!(act = sc.next()).equals("7")) {	 
                    if(act.equals("1")) {
                            System.out.print("Nhập vị trí cần update (a): ");
                            x = sc.nextInt();
                            System.out.print("Nhập giá trị: ");
                            y = sc.nextInt();

                            update(x, y);
                            show();
                    } else if(act.equals("2")) {
                            System.out.print("Nhập đoạn cần update (a-b): ");
                            x = sc.nextInt(); y = sc.nextInt();
                            System.out.print("Nhập giá trị update: ");
                            z = sc.nextInt();

                            update(x, y, z);
                            show();
                    } else if(act.equals("3")) {
                        System.out.print("Nhập giá trị update: ");
                            x = sc.nextInt();

                            update(x);
                            show();
                    } else if(act.equals("4")) {
                            show();
                            System.out.print("Nhập N: "); int n = sc.nextInt();
                            System.err.println("Tổng từ 1 đến " + n + " = " + sum(n));

                    } else if(act.equals("5")){
                            show();
                            System.out.print("Nhập đoạn thực hiện: ");
                            x = sc.nextInt(); y = sc.nextInt();
                            System.err.println("Tổng từ " + x + " đến " + y + " = " + sum(x, y));
                    } else if(act.equals("6")) {
                            System.err.println("Việt và Nghĩa said 'BYE BYE'");
                            break;
                    }
                    System.out.print("Nếu bạn muốn tiếp tục hãy chọn thao tác: ");
                } System.err.println("ERROR"); break;
            } else if(check == 3){
                    System.err.println("Việt và Nghĩa said 'BYE BYE'");
                    break;     
            }
        }
    }
    
    // phan them node
    public static void create_tree() {
    	for (int i = 1; i <= N; i++)
            BITree[i] = i;
        for (int i = 1; i <= N; i++){
            Sum_A[i] = a[i] + Sum_A[i - 1];
            BITree[i] =  Sum_A[i] - Sum_A[(i - (i & -i) + 1) - 1];
        }
    }
    
    // update giá trị trong đoạn a->b
    public static void update(int n, int m, int val) {
    	for (int i = n; i <= m; i++){
            for (int j = i; j <= N; j += j & -j){
                BITree[j] += val;
            }
        }
        for (int i = n; i <= m; i++){
            a[i] += val;
        }
    }
    
    // update giá trị tại vị trí bất kỳ
    public static void update(int i, int val) {
    	int index = i;
        while(index <= N){
            BITree[index] += val;
            index += index & (-index);
        }
        a[i] += val;
    }
    
    //update tất cả phần tử
    public static void update(int val) {
        for (int i = 1; i <= N; i++){
            for (int j = i; j <= N; j += j & -j){
                BITree[j] += val;
            }
        }
        for (int i = 1; i <= N; i++){
            a[i] += val;
        }
    }

    // hàm sum trong đoạn a->b
    public static int sum(int i, int k) {
        return sum(k) - sum(i-1);
    }
    
    //sum từ 1->n
    public static int sum(int n) {
        int ans = 0;
        for (int i = 1; i <= n; i++)
        	ans += a[i];
        return ans;
    }
    
    //view 2 mảng (mảng cây - mảng 1 chiều)
    public static void show() {
    	System.err.print("ARR: ");
    	for (int i = 1; i <= N; i++) 
    		System.err.printf("%d ", a[i]);  
    	System.err.println();
    	System.err.print("BIT: ");
        for (int i = 1; i <= N; i++) 
    		System.err.printf("%d ", BITree[i]);   
        System.err.println();
    }
}