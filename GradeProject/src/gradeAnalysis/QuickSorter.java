package gradeAnalysis;

public class QuickSorter {
    
    public void quick(int Array[], int l, int r)
    {
        int left = l;
        int right = r;
        
        //피봇값을 정한다.
        int pivot = (left+right)/2;
        
        do
        {
            //중간 값이 더 크다면 left 한칸 이동한다.
            while(Array[left]<Array[pivot]) left++;
            //중간 값이 더 작다면 right 한칸 이동한다.
            while(Array[pivot]<Array[right]) right--;
            
            //right 값이 left값보다 크거나 같다면 temp값 변경
            if(left <= right)
            {
                int temp = Array[left];
                Array[left] = Array[right];
                Array[right] = temp;
                
                
                left++;
                right--;
            }
        }while(left <= right); //같거나 왼쪽이 더 크면 while문을 나간다.
        
        //pivot보다 작은 부분과 큰 부분을 재귀 호출 하여 비교해서 정렬
        if(l < right) quick(Array, l, right);
        if(left < r ) quick(Array, left, r);
        
    }
    

}
