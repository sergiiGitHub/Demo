//Расчет максимальной скорости передачи данных
//https://89.209.12.198/cgi-bin/new-client?SID=8d5a8f6a635053ff&action=139&prob_id=10


#include <cstdio>


const int V = 102;
int n, m, a, b, u, v, x, c[V][V], f[V][V], p[V];


void start()
{
    for (int i = 0; i < V; ++i)
    {
        for (int j = 0; j < V; ++j)
        {
            c[i][j] = f[i][j] = 0;
        }
    }
}


bool bfs()
{
    for (int i = 1; i <= n; ++i)
    {
        p[i] = 0;
    }
    int q[V], h = 0, t = 0;
    q[t++] = a;
    p[a] = a;
    while (t > h && !p[b])
    {
        v = q[h++];
        for (u = 1; u <= n; ++u)
        {
            if (c[v][u] - f[v][u] > 0 && !p[u])
            {
                q[t++] = u;
                p[u] = v;
            }
        }
    }
    return (p[b] != 0);
}


int maxflow()
{
    while (bfs())
    {
        u = b, v = p[b];
        int add_flow = c[v][u] - f[v][u];
        u = v;
        while (u != a)
        {
            v = p[u];
            if (c[v][u] - f[v][u] < add_flow)
            {
                add_flow = c[v][u] - f[v][u];
            }
            u = v;
        }

        u = b;
        while (u != a)
        {
            v = p[u];
            f[v][u] += add_flow;
            f[u][v] -= add_flow;
            u = v;
        }
    }

    int flow = 0;
    for (int i = 1; i <= n; ++i)
    {
        flow += f[i][b];
    }
    return flow;
}


int main()
{
    //freopen("j.txt", "r", stdin);
    while (scanf("%d%d%d%d", &n, &m, &a, &b) != -1)
    {
        start();
        for (int i = 0; i < m; ++i)
        {
            scanf("%d%d%d", &u, &v, &x);
            c[u][v] = c[v][u] = x;
        }
        printf("%d\n", maxflow());
    }
}

