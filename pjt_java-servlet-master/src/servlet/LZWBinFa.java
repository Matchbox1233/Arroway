package servlet;

import java.io.PrintWriter;

public class LZWBinFa
{
    public LZWBinFa(PrintWriter out)
    {
        root = fa = new Node();
        this.out = out;
        this.atlag = this.atlagosszeg = this.szorasosszeg = this.atlagdb = this.maxMelyseg = this.melyseg = 0;
    }
    
    public void add(char c)
    {
        if(c == '0')
        {
            if(fa.hasLeft())
            {
                fa = fa.getLeft();
            }
            else
            {
                fa.addLeft(new Node(c));
                fa = root;
            }
        }
        else
        {
            if(fa.hasRight())
            {
                fa = fa.getRight();
            }
            else
            {
                fa.addRight(new Node(c));
                fa = root;
            }
        }
    }

    public Node getRoot()
    {
        return this.root;
    }

    public Node getFa()
    {
        return this.fa;
    }

    public void preorder(Node n, int depth)
    {
        if(n == null)
        {
            return;
        }
        for(int i=0; i < depth; i++)
        {
        	out.print("--");
        }
        out.print(n.getChar() + "(" + depth + ")" +"<br />");
        preorder(n.getLeft(),depth + 1);
        preorder(n.getRight(),depth + 1);
    }

    public void postorder(Node n, int depth)
    {
        if(n == null)
        {
            return;
        }
        postorder(n.getLeft(), depth + 1);
        postorder(n.getRight(), depth + 1);
        for(int i=0; i < depth; i++)
        {
        	out.print("--");
        }
        out.print(n.getChar() + "(" + depth + ")" +"<br />");
    }
    public void inorder(Node n, int depth)
    {
        if(n == null)
        {
            return;
        }
        inorder(n.getLeft(), depth + 1);
        for(int i=0; i < depth; i++)
        {
        	out.print("--");
        }
        out.print(n.getChar() + "(" + depth + ")" +"<br />");
        inorder(n.getRight(), depth + 1);
    }
    
    public void writeHTML(String s)
    {
    	out.print("<div>");
    	if(s.contains("preorder"))
    	{
    		preorder(this.root, 0);
    	}
    	else if(s.contains("inorder"))
    	{
    		inorder(this.root, 0);
    	}
    	else
    	{
    		postorder(this.root, 0);
    	}
    	out.print("</div>");
    	
    }
    
    public double getSzoras()
    {
    	this.rszoras(this.root);
    	return this.szorasosszeg;
    }
    public double getMelyseg()
    {
    	this.maxMelyseg = this.melyseg = 0;
    	this.rmelyseg(this.root);
    	return this.maxMelyseg - 1;
    }
    public double getAtlag()
    {
    	this.ratlag(this.root);
    	return this.atlag;
    }
    
    
    void rmelyseg (Node elem)
    {
        if (elem != null)
        {
            ++melyseg;
            if (melyseg > maxMelyseg)
                maxMelyseg = melyseg;
            rmelyseg (elem.getRight());
            // ez a postorder bejáráshoz képest
            // 1-el nagyobb mélység, ezért -1
            rmelyseg (elem.getLeft());
            --melyseg;
        }
    }

    void ratlag (Node elem)
    {
        if (elem != null)
        {
            ++melyseg;
            ratlag (elem.getRight());
            ratlag (elem.getLeft());
            --melyseg;
            if (elem.hasLeft() && elem.hasRight())
            {
                ++atlagdb;
                atlagosszeg += melyseg;
            }
        }
    }

    void rszoras (Node elem)
    {
        if (elem != null)
        {
            ++melyseg;
            rszoras (elem.getRight());
            rszoras (elem.getLeft());
            --melyseg;
            if (elem.hasLeft() && elem.hasRight())
            {
                ++atlagdb;
                szorasosszeg += ((melyseg - atlag) * (melyseg - atlag));
            }
        }
    }
    double melyseg;
    double atlagdb;
    double szorasosszeg;
    double atlag;
    double maxMelyseg;
    double atlagosszeg;
    PrintWriter out;
    Node root;
    Node fa;
}




