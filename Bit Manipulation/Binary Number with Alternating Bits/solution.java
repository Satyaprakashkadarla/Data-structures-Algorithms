class Solution
{
    public boolean hasAlternatingBits(int n)
    {
        int p = n%2;
        n /= 2;

        while(n>0)
        {
            if(n%2==p)
                return false;

            p = n%2;
            n /= 2;
        }

        return true;
    }
}