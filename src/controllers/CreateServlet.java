package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import util.DBUtil;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token !=null && _token.equals(request.getSession().getId())){

            //ﾃﾞｰﾀﾍﾞｰｽに干渉するため、DAOで窓口を用意
            EntityManager em = DBUtil.createEntityManager();

            Task t = new Task();

            //タスク型インスタンスtに値をセットする
            String content = request.getParameter("content");
            t.setContent(content);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            t.setCreated_at(currentTime);
            t.setUpdated_at(currentTime);

            //ﾃﾞｰﾀﾍﾞｰｽにtをコミットする、一連の流れ
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
            em.close();

            //indexへ遷移、
            //（ﾘｸｴｽﾄｽｺｰﾌﾟを使わないのでこの記述になる）
            response.sendRedirect(request.getContextPath() + "/index");
        }
    }

}
