//Problem N-[Advanced] Hanaro
//https://89.209.12.198/cgi-bin/new-client?SID=8d5a8f6a635053ff&action=139&prob_id=14


#include <cstdio>
#define long long long


const int N = 1001;
int n;
long x[N], y[N];
double er;


struct Dsu
{
    int p[N];

    Dsu()
    {
        for (int i = 0; i < n; ++i) p[i] = i;
    }

    int find(int u)
    {
        if (p[u] == u) return u;
        return p[u] = find(p[u]);
    }

    bool merge(int u, int v)
    {
        u = find(u);
        v = find(v);
        if (u != v)
        {
            if ((u ^ v) & 1) u ^= v ^= u ^= v;
            p[v] = u;
            return true;
        }
        return false;
    }
};


struct Edge
{
    int from, to;
    long cost;

    Edge(int a = 0, int b = 0, long c = 0) : from(a), to(b), cost(c)
    {
    }

    bool operator <(const Edge& o) const
    {
        return cost < o.cost;
    }
};


long sqr(long a)
{
    return a * a;
}


long dist(long i, long j)
{
    return sqr(x[i] - x[j]) + sqr(y[i] - y[j]);
}


void merge(Edge* e, int l, int m, int r)
{
    Edge* t = new Edge[r - l + 1];
    int i = l, j = m + 1, k = 0;
    while (i <= m && j <= r)
    {
        if (e[i] < e[j]) t[k++] = e[i++];
        else t[k++] = e[j++];
    }
    while (i <= m)
    {
        t[k++] = e[i++];
    }
    while (j <= r)
    {
        t[k++] = e[j++];
    }
    for (int k = 0; k < r - l + 1; ++k)
    {
        e[l + k] = t[k];
    }
    delete[] t;
}


void sort(Edge* e, int l, int r)
{
    if (r - l < 1) return;
    int mid = (l + r) / 2;
    sort(e, l, mid);
    sort(e, mid + 1, r);
    merge(e, l, mid, r);
}


int main()
{
#ifdef LALALA
    freopen("n.txt", "r", stdin);
#endif // LALALA
    while (scanf("%d", &n) != -1)
    {
        for (int i = 0; i < n; ++i) (void)scanf("%d", (x + i));
        for (int i = 0; i < n; ++i) (void)scanf("%d", (y + i));
        (void)scanf("%lf", &er);

        Edge* e = new Edge[n * (n - 1) / 2];
        for (int i = 0, k = 0; i < n; ++i)
        {
            for (int j = i + 1; j < n; ++j)
            {
                e[k++] = Edge(i, j, dist(i, j));
            }
        }
        sort(e, 0, n * (n - 1) / 2 - 1);
        Dsu dsu;
        long ans = 0;
        for (int i = 0; i < n * (n - 1) / 2; ++i)
        {
            int u = e[i].from;
            int v = e[i].to;
            if (dsu.merge(u, v))
            {
                ans += e[i].cost;
            }
        }
        printf("%lld\n", (long)(er * ans + 0.5));
    }
}
