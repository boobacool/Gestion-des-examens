package com.boobacool.notemanagement.etats;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boobacool.notemanagement.dao.AnneeDAO;
import com.boobacool.notemanagement.dao.AntenneDAO;
import com.boobacool.notemanagement.dao.ClasseDAO;
import com.boobacool.notemanagement.dao.ExaEpreuveDAO;
import com.boobacool.notemanagement.dao.ExamenDAO;
import com.boobacool.notemanagement.dao.FiliereDAO;
import com.boobacool.notemanagement.dao.InscriptionDAO;
import com.boobacool.notemanagement.dao.NiveauDAO;
import com.boobacool.notemanagement.dao.NotationDAO;
import com.boobacool.notemanagement.models.Annee;
import com.boobacool.notemanagement.models.Antenne;
import com.boobacool.notemanagement.models.Classe;
import com.boobacool.notemanagement.models.ExaEpreuve;
import com.boobacool.notemanagement.models.Examen;
import com.boobacool.notemanagement.models.Filiere;
import com.boobacool.notemanagement.models.Inscription;
import com.boobacool.notemanagement.models.Niveau;
import com.boobacool.notemanagement.models.Notation;
import com.boobacool.notemanagement.models.Resultat;
import com.boobacool.notemanagement.models.ResultatList;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
@RequestMapping("/generatePDF")
public class GenerationPDFController {

	@Autowired
	private ServletContext context;
	@Autowired
	private AnneeDAO anneeDAO;

	@Autowired
	private FiliereDAO filiereDAO;

	@Autowired
	private NiveauDAO niveauDAO;

	@Autowired
	private AntenneDAO antenneDAO;

	@Autowired
	private ClasseDAO classeDAO;

	@Autowired
	private ExamenDAO examenDAO;

	@Autowired
	private InscriptionDAO inscriptionDAO;

	@Autowired
	private ExaEpreuveDAO exaEpreuveDAO;

	@Autowired
	private NotationDAO notationDAO;

	public boolean createPdf(ResultatList resultatList, Examen exa, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		int taille = resultatList.getListeMatieres().size() + 3;
		float[] format = new float[taille];

		format[0] = 6f;
		format[1] = 3f;
		format[2] = 2f;
		for (int i = 3; i < taille; i++) {
			format[i] += 2f;

		}

		// Obtention du nom

		String nomfichier = resultatList.getClasse().getLib() + " " + exa.getLib() + ".pdf";
		Document document = new Document(PageSize.A3.rotate());
		try {
			String filePath = context.getRealPath("/ressources/etats");
			File file = new File(filePath);
			boolean exists = new File(filePath).exists();
			if (!exists) {
				new File(filePath).mkdirs();
			}
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + nomfichier));
			document.open();
			Font mainFont = FontFactory.getFont("Arial", 30, BaseColor.BLACK);

			Paragraph paragraph = new Paragraph(
					"RESULTAT " + exa.getLib() + " " + resultatList.getClasse().getAntenne().getLib()+" "
							+ ""+resultatList.getClasse().getLib() + " " + resultatList.getAnnee().getLib(), mainFont);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setIndentationLeft(50);
			paragraph.setIndentationRight(50);
			paragraph.setSpacingAfter(10);
			document.add(paragraph);
/*
			Paragraph paragraph1 = new Paragraph(
					resultatList.getClasse().getLib() + " " + resultatList.getAnnee().getLib(), mainFont);
			paragraph1.setAlignment(Element.ALIGN_CENTER);
			paragraph1.setIndentationLeft(50);
			paragraph1.setIndentationRight(50);
			paragraph1.setSpacingAfter(10);
			document.add(paragraph1);
*/
			PdfPTable table = new PdfPTable(taille);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10);
			table.setSpacingAfter(10);
			Font tableHeader = FontFactory.getFont("Arial", 15, BaseColor.BLACK);
			Font tableBody = FontFactory.getFont("Arial", 15, BaseColor.BLACK);

			// float[] columnWidfhs = format;
			table.setWidths(format);
			PdfPCell etudiant = new PdfPCell(new Paragraph("NOM & PRENOMS", tableHeader));
			etudiant.setBorderColor(BaseColor.BLACK);
			etudiant.setPadding(10);
			etudiant.setHorizontalAlignment(Element.ALIGN_CENTER);
			etudiant.setVerticalAlignment(Element.ALIGN_CENTER);
			etudiant.setBackgroundColor(BaseColor.GRAY);
			etudiant.setExtraParagraphSpace(5f);
			table.addCell(etudiant);
			PdfPCell decision = new PdfPCell(new Paragraph("DECISION", tableHeader));
			decision.setBorderColor(BaseColor.BLACK);
			decision.setPadding(10);
			decision.setHorizontalAlignment(Element.ALIGN_CENTER);
			decision.setVerticalAlignment(Element.ALIGN_CENTER);
			decision.setBackgroundColor(BaseColor.GRAY);
			decision.setExtraParagraphSpace(5f);
			table.addCell(decision);
			PdfPCell moyenne = new PdfPCell(new Paragraph("MOYENNE", tableHeader));
			moyenne.setBorderColor(BaseColor.BLACK);
			moyenne.setPadding(10);
			moyenne.setHorizontalAlignment(Element.ALIGN_CENTER);
			moyenne.setVerticalAlignment(Element.ALIGN_CENTER);
			moyenne.setBackgroundColor(BaseColor.GRAY);
			moyenne.setExtraParagraphSpace(5f);
			table.addCell(moyenne);

			DecimalFormat dec = new DecimalFormat("#0.00");

			for (int i = 0; i < taille - 3; i++) {

				// System.out.println(resultatList.getListeMatieres().get(i).getEpreuve().getLib().toUpperCase());
				PdfPCell matiere = new PdfPCell(new Paragraph(
						resultatList.getListeMatieres().get(i).getEpreuve().getLib().toUpperCase(), tableHeader));
				matiere.setBorderColor(BaseColor.BLACK);
				matiere.setPadding(10);
				matiere.setHorizontalAlignment(Element.ALIGN_CENTER);
				matiere.setVerticalAlignment(Element.ALIGN_CENTER);
				matiere.setBackgroundColor(BaseColor.GRAY);
				matiere.setExtraParagraphSpace(5f);
				table.addCell(matiere);
			}

			for (int i = 0; i < resultatList.getListeResultats().size(); i++) {

				PdfPCell nomPrenom = new PdfPCell(new Paragraph(resultatList.getListeResultats().get(i).getNom() + " "
						+ resultatList.getListeResultats().get(i).getPrenoms(), tableBody));
				nomPrenom.setBorderColor(BaseColor.BLACK);
				nomPrenom.setPaddingLeft(10);
				nomPrenom.setHorizontalAlignment(Element.ALIGN_LEFT);
				nomPrenom.setVerticalAlignment(Element.ALIGN_CENTER);
				nomPrenom.setBackgroundColor(BaseColor.WHITE);
				nomPrenom.setExtraParagraphSpace(5f);
				table.addCell(nomPrenom);

				if (resultatList.getListeResultats().get(i).getConduite().equals("bonne")) {

					PdfPCell decisions = new PdfPCell(
							new Paragraph(resultatList.getListeResultats().get(i).getDecision(), tableBody));
					decisions.setBorderColor(BaseColor.BLACK);
					decisions.setPaddingLeft(10);
					decisions.setHorizontalAlignment(Element.ALIGN_CENTER);
					decisions.setVerticalAlignment(Element.ALIGN_CENTER);
					decisions.setBackgroundColor(BaseColor.WHITE);
					decisions.setExtraParagraphSpace(5f);
					table.addCell(decisions);

					PdfPCell moyennes = new PdfPCell(
							new Paragraph(dec.format(resultatList.getListeResultats().get(i).getMoyenne()), tableBody));
					moyennes.setBorderColor(BaseColor.BLACK);
					moyennes.setPaddingLeft(10);
					moyennes.setHorizontalAlignment(Element.ALIGN_CENTER);
					moyennes.setVerticalAlignment(Element.ALIGN_CENTER);
					moyennes.setBackgroundColor(BaseColor.WHITE);
					moyennes.setExtraParagraphSpace(5f);
					table.addCell(moyennes);

					for (int j = 0; j < resultatList.getListeResultats().get(i).getListeNotation().size(); j++) {
						PdfPCell notes = new PdfPCell(new Paragraph(
								resultatList.getListeResultats().get(i).getListeNotation().get(j).getNote() + "",
								tableBody));
						notes.setBorderColor(BaseColor.BLACK);
						notes.setPaddingLeft(10);
						notes.setHorizontalAlignment(Element.ALIGN_CENTER);
						notes.setVerticalAlignment(Element.ALIGN_CENTER);
						notes.setBackgroundColor(BaseColor.WHITE);
						notes.setExtraParagraphSpace(5f);
						table.addCell(notes);
					}
				} else {
					PdfPCell fusion = new PdfPCell(new Paragraph("INDISCIPLINE",tableBody));
					fusion.setColspan(resultatList.getListeResultats().get(i).getListeNotation().size()+2);
					fusion.setHorizontalAlignment(Element.ALIGN_CENTER);
					fusion.setVerticalAlignment(Element.ALIGN_CENTER);
					fusion.setBackgroundColor(BaseColor.RED);
					fusion.setExtraParagraphSpace(5f);
					table.addCell(fusion);

				}

			}
			document.add(table);
			PdfPTable directeur = new PdfPTable(3);
			directeur.setWidthPercentage(100);
			PdfPCell c = new PdfPCell(new Paragraph(" "));
			PdfPCell c1 = new PdfPCell(new Paragraph("\n\nDIRECTEUR PEDAGOGIQUE\n\n\n\n\n\n\n\n  M. BAFREMORY FOFANA"));
			c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			c.setBorder(Rectangle.NO_BORDER);
			c1.setBorder(Rectangle.NO_BORDER);
			directeur.addCell(c);
			directeur.addCell(c);
			directeur.addCell(c1);
			document.add(directeur);
			document.close();
			writer.close();
			return true;
		} catch (Exception e) {
			return false;

		}
	}

	@GetMapping("/formulaire")
	public String formulaire(Model model) {

		model.addAttribute("listeAnnees", anneeDAO.findAll());
		model.addAttribute("listeAntennes", antenneDAO.findAll());
		model.addAttribute("listeNiveaux", niveauDAO.findAll());
		model.addAttribute("listeFilieres", filiereDAO.findAll());
		model.addAttribute("listeExamens", examenDAO.findAll());
		return "pdf/formulaire";
	}

	@GetMapping("/createpdf")
	public void createPDF(HttpServletRequest request, HttpServletResponse response, Integer ida, Integer idant,
			Integer idniv, Integer idfil, Integer idexa) {

		Antenne ant = antenneDAO.findOne(idant);
		Examen exa = examenDAO.findOne(idexa);
		Annee an = anneeDAO.findOne(ida);
		Niveau niv = niveauDAO.findOne(idniv);
		Filiere fil = filiereDAO.findOne(idfil);

		Classe classe = null;
		classe = classeDAO.findByAntenneAndFiliereAndNiveau(ant, fil, niv);

		List<Inscription> listeInscrires = inscriptionDAO.findByClasseAndAnnee(classe, an);
		List<ExaEpreuve> listeepreuves = exaEpreuveDAO.findByAnneeAndClasseAndAndExamen(an, classe, exa);

		ResultatList resultatList = new ResultatList(classe, an, listeepreuves);

		if (listeInscrires.size() == 0 || listeepreuves.size() == 0) {
			// model.addAttribute("erreur", "cette classe est vide ou la liste de matières
			// est vide");
		} else {
			for (int i = 0; i < listeInscrires.size(); i++) {
				List<Notation> listeNotation = new ArrayList<>();
				Double somme = 0.0;
				Integer coef = 0;
				Resultat resultat = new Resultat();
				resultat.setNom(listeInscrires.get(i).getEtudiant().getNom());
				resultat.setPrenoms(listeInscrires.get(i).getEtudiant().getPrenom());
				resultat.setConduite(listeInscrires.get(i).getConduite());
				for (int j = 0; j < listeepreuves.size(); j++) {
					Notation nota = new Notation();
					nota = notationDAO.findByInscriptionAndExaEpreuve(listeInscrires.get(i), listeepreuves.get(j));
					if (nota == null) {
						Notation nota1 = new Notation();
						nota1.setExaEpreuve(listeepreuves.get(j));
						nota1.setInscription(listeInscrires.get(i));
						nota1.setNote(0.0);
						resultat.getListeNotation().add(nota1);
						somme = somme + (nota1.getNote() * nota1.getExaEpreuve().getCoef());
						coef = coef + nota1.getExaEpreuve().getCoef();
						listeNotation.add(nota1);
					} else {
						resultat.getListeNotation().add(nota);
						somme = somme + (nota.getNote() * nota.getExaEpreuve().getCoef());
						coef = coef + nota.getExaEpreuve().getCoef();
						listeNotation.add(nota);
					}

				}
				resultat.setMoyenne(somme / coef);
				resultat.setDecision(appreciation1(somme / coef));
				resultat.setListeNotation(listeNotation);
				resultatList.getListeResultats().add(resultat);

			}

		}

		boolean isFlag = createPdf(resultatList, exa, context, request, response);

		if (isFlag) {
			String fullPath = request.getServletContext()
					.getRealPath("/ressources/etats/" + classe.getLib() + " " + exa.getLib() + ".pdf");
			fileDownload(fullPath, response, classe.getLib() + " " + exa.getLib() + ".pdf");

		}

	}

	private void fileDownload(String fullPath, HttpServletResponse response, String filename) {

		File file = new File(fullPath);
		final int BUFFER_SIZE = 4096;
		if (file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				String mimeType = context.getMimeType(fullPath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachment;filename=" + filename);
				OutputStream outputStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);

				}
				inputStream.close();
				outputStream.close();
				file.delete();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	
	public String appreciation1(Double moyenne) {

		if (moyenne < 10)
			return "ECHEC";
		else
			return "ADMIS";
		
	}

	public String appreciation(Double moyenne) {

		if (moyenne < 6)
			return "Très Failble";
		else if (moyenne >= 6 && moyenne < 9.5)
			return "Faible";
		else if (moyenne >= 9.5 && moyenne < 10)
			return "Insuffisant";
		else if (moyenne >= 10 && moyenne < 12)
			return "Passable";
		else if (moyenne >= 12 && moyenne < 13)
			return "Bien";
		else if (moyenne >= 13 && moyenne < 15)
			return "Assez-Bien";
		else if (moyenne >= 15 && moyenne < 17)
			return "Très-Bien";
		else
			return "Excellent";
	}

}
