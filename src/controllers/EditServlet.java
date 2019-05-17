package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import util.DBUtil;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //DAOでデータベースとの窓口を作成
        EntityManager em = DBUtil.createEntityManager();

        //リクエストパラメータを利用し、
        //該当IDのタスク１件のみをデータベースから取得
        Task t = em.find(Task.class,Integer.parseInt(request.getParameter("id")));

        em.close();

        //tとセッションIDをリクエストスコープに登録
        request.setAttribute("task", t);
        request.setAttribute("_token",request.getSession().getId());

        if(t != null){
            //task.idをセッションスコープに登録,
            //(task.idは、ﾘｸｴｽﾄｽｺｰﾌﾟを２回使用しても良いが、今回はｾｯｼｮﾝｽｺｰﾌﾟを使用）
            request.getSession().setAttribute("task_id", t.getId());
        }

        //該当IDのTask型tと、セッションIDをリクエストスコープに、
        //task_idをセッションスコープに入れてedit.jspに遷移
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/edit.jsp");
        rd.forward(request, response);
    }

}
