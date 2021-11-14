package com.example.demo;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Repository.AdminsRepository;
import com.example.demo.Repository.ClientRepository;
import com.example.demo.Repository.ProduitRepository;
import com.example.demo.entities.Admins;
import com.example.demo.entities.Client;
import com.example.demo.entities.Produit;

@Controller
public class MyController {
	
@Autowired															/////////////////////////////////////////////////////
ClientRepository cr;
@Autowired
AdminsRepository ar;
@Autowired
ProduitRepository pr;




	
@RequestMapping("/")

public String home(Model m,HttpServletRequest request)
{
	
	List<Produit> list=pr.findAll();
	
	m.addAttribute("listPoduit", list);
	List<String> listeMarque=pr.findAllByMarque();
	m.addAttribute("listeMarque", listeMarque);
	
	
	
//	HttpSession session=request.getSession();


//	HttpSession session = request.getSession(false);
//	if (session == null) {
//	    System.out.println("session non creer");
//	    
//	} else {
//		String user = (String) session.getAttribute("utilisateur");
//		System.out.println(user);
//		m.addAttribute("USER",user);
//	}
	
	return "index.html";
}




@PostMapping(value = "/register")

public String register(@ModelAttribute("client")Client client)
{
	cr.save(client);
	return "index.html";
}




@PostMapping(value = "/signC")

public String signC(@RequestParam("mail")String mail,@RequestParam("mdp")String mdp, Model m,HttpServletRequest request)
{
	
	
List<Client> list=cr.findByMail(mail);
	
	if(list.isEmpty())
	{
		m.addAttribute("erreurLogC", "Votre compte n'existe pas");
		
		System.out.println("compte no");
		
		return "loginClient.html";
	}
	else if(list.get(0).getMdp().equals(mdp)==false)
	{
		System.out.println("mdp no");

		m.addAttribute("erreurLogC", "Votre mot de passe n'est pas correcte");
		return "loginClient.html";
	}
	else
	{
		m.addAttribute("erreurLogC", null);
		System.out.println();
		System.out.println("c bon");
		HttpSession session=request.getSession(true);	
		session.setAttribute("utilisateur", list.get(0)); ////////////////////
		return "redirect:/";
	}
	

}


@RequestMapping("/formulaire")
public String formulaire()
{
	return "formulaire.html";
}

@RequestMapping("/checkSession")

public String checkSession(HttpServletRequest request, Model m)
{
	
	HttpSession session = request.getSession(false);
	if(session!=null)
	{
		
		Client user =(Client) session.getAttribute("utilisateur");
		m.addAttribute("user", user);
		return "myAccount.html";
	}
	else
	{
		return "loginClient.html";
		
	}
}

@RequestMapping("/loginClient")

public String loginClient()
{
	return "loginClient.html";
}

@RequestMapping("/logAdmin")
public String loginAdmin()
{
	return "loginAdmin.html";
}





@PostMapping(value="/signA")

public String signA(@RequestParam("mail")String mail,@RequestParam("mdp")String mdp, Model m,HttpServletRequest request) {
	
	if(request.getSession(false)!=null)
	{
		return "admin.html";
	}
	
List<Admins> list=ar.findByMail(mail);
	
	if(list.isEmpty())
	{
		m.addAttribute("erreurLogA", "Votre compte n'existe pas");
		
		System.out.println("compte no");
		
		return "loginAdmin.html";
	}
	else if(list.get(0).getMdp().equals(mdp)==false)
	{
		System.out.println("mdp no");

		m.addAttribute("erreurLogA", "Votre mot de passe n'est pas correcte");
		return "loginAdmin.html";
	}
	else
	{
		m.addAttribute("erreurLogA", null);
		List<Admins> listeA=ar.findAll();
		m.addAttribute("listeAdmin",listeA);
		HttpSession session=request.getSession(true);
		session.setAttribute("admin", list.get(0));
		System.out.println(session.getAttribute("admin"));
		return "admin.html";
	}

}

@RequestMapping(value="ListeClients")
public String ListeClients(Model m)
{
	List<Client> listeC=cr.findAll();
	m.addAttribute("listeC",listeC);
	
	return "ListeClients.html";
}

@RequestMapping(value="ListeProduits")
public String ListeProduits(Model m)
{
	List<Produit> listeP=pr.findAll();
	m.addAttribute("listeP",listeP);
	
	return "ListeProduits.html";
}




@RequestMapping(value = "/decoA")
public String decoA(HttpServletRequest request) {
	request.getSession().invalidate();
	return "loginAdmin.html";
}





@RequestMapping(value = "/panier")
public String panier()
{
	return "panier.html";
}




@RequestMapping(value = "voirDetail")
public String voirDetail(Model m,@RequestParam(name="id")Long id)
{
	
List<Produit> list=pr.rechercheParId(id);
m.addAttribute("produitDetail", list.get(0));
//	System.out.println(list);
	
	
	return "produitExample.html";
}


@RequestMapping(value = "seDeconnecter")

public String seDeconnecter(HttpServletRequest request)
{
	
	request.getSession().invalidate();
	
	
	return "loginClient";
}

//@RequestMapping(value = "passerCommande")
//public String passerCommande(Model m) {
////	List<Produit> list=pr.rechercheParId(id);
////	System.out.println("list");
//	return "panier.html";
//}


@RequestMapping(value="ajouterPanier")
public String ajouterPanier(Model m,HttpServletRequest request) {

	HttpSession session = request.getSession(false);
	if (session == null) {
	    System.out.println("session non creer");
	    return "loginClient.html";
	} else {
		
		return "panier.html";
	}

}
@RequestMapping(value="tousProduits")
public String tousProduits(Model m, @RequestParam(name="page", defaultValue="0")int p)
{
	Page<Produit> page = pr.findAll(PageRequest.of(p,5));
	m.addAttribute("page_produits", page);
	int nbPages = page.getTotalPages();
	int pages[] = new int[nbPages];
	for(int i=0;i<nbPages; i++)
		pages[i]=i;
	m.addAttribute("pages", pages);
	return "produits.html";
}

@RequestMapping(value="ajoutpr")
public String ajoutpr() {
	return "AjoutProduit.html";
}

@PostMapping(value = "/AjoutProduit")

public String AjoutProduit(@ModelAttribute("produit")Produit produit)
{
	pr.save(produit);
	return "redirect:/ListeProduits";
}


@PostMapping(value = "/Maj")

public String AjoutProduit(@ModelAttribute("client")Client client,HttpServletRequest request)
{
	cr.save(client);
	request.getSession().setAttribute("utilisateur", client);
	return "redirect:/checkSession";
}


}
