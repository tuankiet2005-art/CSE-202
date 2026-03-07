import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class EIUWBT {

    public static void main(String[] args) throws IOException {
        InputReader rd = new InputReader(System.in);
        StringBuilder sb = new StringBuilder();

        int n = rd.nextInt();

        Vertex[] graph = new Vertex[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new Vertex(i);
        }

        long totalWeight = 0;

        for (int i = 1; i <= n; i++) {
            graph[i].weight = rd.nextInt();
            totalWeight += graph[i].weight;
        }

        for (int i = 0; i < n-1; i++) {
            int u = rd.nextInt();
            int v = rd.nextInt();
            graph[u].addAdjVertex(graph[v]);
            graph[v].addAdjVertex(graph[u]);
        }

        long minDif = Long.MAX_VALUE;
        int minId = -1;
        long bestTW1 = -1, bestTW2 = -1;

        for (int i = 1; i <= n; i++) {
            if (graph[i].adjVertexs.size() == 2) {
                graph[i].visited = true;
                long TW1 = dfs(graph[i].adjVertexs.get(0));
                long TW2 = Math.abs(totalWeight - TW1 - graph[i].weight);
                long currentMinDif = Math.abs(TW1-TW2);
                if(currentMinDif < minDif) {
                    minId = i;
                    bestTW1 = TW1;
                    bestTW2 = TW2;
                    minDif = currentMinDif;
                }
                resetGraph(graph);
            }
        }

        System.out.println(minId == -1 ? -1 : minId + " " + Math.min(bestTW1, bestTW2) + " " + Math.max(bestTW1, bestTW2));

    }

    static void resetGraph(Vertex[] graph){
        for (int i = 1; i < graph.length; i++) {
            graph[i].visited = false;
        }
    }

    static long dfs(Vertex vertex) {
        vertex.visited = true;
        long totalWeight = vertex.weight;
        for (Vertex adjVertex : vertex.adjVertexs) {
            if(!adjVertex.visited) {
                adjVertex.visited = true;
                totalWeight += dfs(adjVertex);
            }
        }
        return totalWeight;
    }

    static class Vertex {
        int id;
        List<Vertex> adjVertexs = new ArrayList<>();
        boolean visited;
        int weight;

        public Vertex(int id) {
            this.id = id;
        }

        public void addAdjVertex(Vertex e) {
            this.adjVertexs.add(e);
        }
    }

    static class InputReader {
        private byte[] inbuf = new byte[2 << 23];
        public int lenbuf = 0, ptrbuf = 0;
        public InputStream is;

        public InputReader(InputStream stream) throws IOException {

            inbuf = new byte[2 << 23];
            lenbuf = 0;
            ptrbuf = 0;
            is = System.in;
            lenbuf = is.read(inbuf);
        }

        public InputReader(FileInputStream stream) throws IOException {
            inbuf = new byte[2 << 23];
            lenbuf = 0;
            ptrbuf = 0;
            is = stream;
            lenbuf = is.read(inbuf);
        }

        public boolean hasNext() throws IOException {
            if (skip() >= 0) {
                ptrbuf--;
                return true;
            }
            return false;
        }

        public String nextLine() throws IOException {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!isSpaceChar(b) && b != ' ') { // when nextLine, ()
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        public String next() {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b
                                        // != ' ')
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        private int readByte() {
            if (lenbuf == -1)
                throw new InputMismatchException();
            if (ptrbuf >= lenbuf) {
                ptrbuf = 0;
                try {
                    lenbuf = is.read(inbuf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (lenbuf <= 0)
                    return -1;
            }
            return inbuf[ptrbuf++];
        }

        private boolean isSpaceChar(int c) {
            return !(c >= 33 && c <= 126);
        }

        private double nextDouble() {
            return Double.parseDouble(next());
        }

        public Character nextChar() {
            return skip() >= 0 ? (char) skip() : null;
        }

        private int skip() {
            int b;
            while ((b = readByte()) != -1 && isSpaceChar(b))
                ;
            return b;
        }

        public int nextInt() {
            int num = 0, b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
                ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }

            while (true) {
                if (b >= '0' && b <= '9') {
                    num = num * 10 + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }

        public long nextLong() {
            long num = 0;
            int b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
                ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }

            while (true) {
                if (b >= '0' && b <= '9') {
                    num = num * 10 + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }
    }

}