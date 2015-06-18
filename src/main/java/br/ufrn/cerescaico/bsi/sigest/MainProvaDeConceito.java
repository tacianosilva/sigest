/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrn.cerescaico.bsi.sigest;

import java.util.List;

import br.ufrn.cerescaico.bsi.sigest.bo.EmpresaBO;
import br.ufrn.cerescaico.bsi.sigest.bo.EstagioBO;
import br.ufrn.cerescaico.bsi.sigest.bo.NegocioException;
import br.ufrn.cerescaico.bsi.sigest.model.Empresa;
import br.ufrn.cerescaico.bsi.sigest.model.Estagio;

/**
 *
 * @author taciano
 */
public class MainProvaDeConceito {

    public static void main(String[] args) throws NegocioException {

        //Professor taciano = new Professor(1, 1721652, "Taciano de Morais Silva");
        //Professor fabricio = new Professor(1, 1721653, "Fabrício");
        //
        //Curso curso = new Curso();
        //curso.setNome("Bacharelado em Sistemas de Informação");

        Estagio estagio = new Estagio();
        estagio.setDescricao("Kurtição");
        //estagio.se
//		curso.setCoordenadorCurso(taciano);
//		curso.setViceCoordenadorCurso(fabricio);
//		curso.setCoordenadorEstagios(fabricio);
        EstagioBO estagioBO = new EstagioBO();
        estagioBO.inserir(estagio);

        //CursoBO cursoBO = new CursoBO();
        //Curso salvo = cursoBO.inserir(curso);
        //Empresa

        Empresa empresa = new Empresa();
        empresa.setNome("Kurtição");
        empresa.setCnpj("123456");

        /*
        CursoBO cursoBO = new CursoBO();
        Curso salvo = cursoBO.inserir(curso);

        //System.out.println("Salvo no BD: " + salvo);

        //List<Curso> lista = cursoBO.listar();
        //for (Curso c : lista) {
        //	System.out.println("lista: " + c);
        //}

        List<Estagio> listaestagio = estagioBO.listar();

        for (Estagio c : listaestagio) {
            System.out.println("lista: " + c);
        }
        */

        //Cadastrar Empresa

        EmpresaBO empresaBO = new EmpresaBO();
        Empresa salva = empresaBO.inserir(empresa);

        System.out.println("Salva no BD: " + salva);

        List<Empresa> empresas = empresaBO.listar();
        for (Empresa e : empresas) {
            System.out.println("lista: " + e);
        }

        /*
         * Evento evento = new Evento();
         *
         * evento.setNome("Jats 2012");
         * evento.setDescricao("Jornada de Atualização Tecnológica");
         *
         * EventoBO eventoMB = new EventoBO(); eventoMB.inserir(evento);
         *
         * System.out.println(System.getProperty("user.dir"));
         *
         * List<Evento> eventos = eventoMB.listar();
         *
         * for (Evento ev : eventos) { System.out.println(ev); }
         *
         * long start = System.currentTimeMillis(); //Preparing parameters Map
         * parameters = new HashMap(); //parameters.put("ReportTitle",
         * "Address Report"); //parameters.put("DataFile",
         * "CustomBeanFactory.java - Bean Array");
         *
         * JRBeanCollectionDataSource data = new
         * JRBeanCollectionDataSource(eventos);
         *
         * System.err.println("Filling time : " + (System.currentTimeMillis() -
         * start));
         *
         * String jasperFile = System.getProperty("user.dir")+
         * "/src/main/webapp/reports/listaEventos.jrxml";
         *
         * JasperPrint jasperPrint; try { JasperReport jasperReport =
         * JasperCompileManager.compileReport(jasperFile); jasperPrint =
         * JasperFillManager.fillReport(jasperReport, parameters, data);
         * //executa o relatório JasperViewer.viewReport(jasperPrint); } catch
         * (JRException ex) {
         * Logger.getLogger(MainProvaDeConceito.class.getName()).log(Level.SEVERE, null, ex); }
         */

        /*
         * Preenche o relatório com os dados. Gera o arquivo
         * BibliotecaPessoal.jrprint
         */
        // JasperFillManager.fillReportToFile( jasperFile, parametros, jrRS );

        /*
         * Exporta para o formato PDF
         */
        // JasperExportManager.exportReportToPdfFile(
        // "untitled_report_1.jrprint" );
    }
}
