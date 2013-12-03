<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
	xmlns:xsd="http://www.w3.org/2001/XMLSchema#" xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#"
	xmlns:owl="http://www.w3.org/2002/07/owl#" xmlns:oor="http://www.ccs.neu.edu/home/kenb/ontologies/usecases#"
	xmlns:dc="http://purl.org/dc/elements/1.1/" xml:base="http://www.ccs.neu.edu/home/kenb/ontologies/usecases#">
	<xsl:output method="html" indent="yes" version="4.0" />
	<xsl:template match="/rdf:RDF">
		<html xmlns="http://www.w3.org/1999/xhtml">
			<head>
				<!--
					Author: Reality Software Website: http://www.realitysoftware.ca
					Note: This is a free template released under the Creative Commons
					Attribution 3.0 license, which means you can use it in any way you
					want provided you keep the link to the author intact.
				-->
				<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
				<title>
					<xsl:value-of select='/rdf:RDF/owl:Ontology/rdfs:comment' />
				</title>
				<link href="auxfiles/style.css" rel="stylesheet" type="text/css" />
			</head>
			<body>
				<div id="container"><!-- header -->
					<div id="header">
						<div id="logo">
							<a href="#">
								<xsl:value-of select='/rdf:RDF/oor:System/oor:name' />
							</a>
							<br />
							Version:
							<xsl:value-of select='/rdf:RDF/oor:System/oor:version' />
						</div>
						<div id="menu">
							<ul>
								<li>
									<a href="index.xml">Home</a>
								</li>
								<xsl:for-each select="link">
									<li>
										<xsl:element name="a">
											<xsl:attribute name="href">
										<xsl:value-of select='@href' />
									</xsl:attribute>
											<xsl:value-of select="@name" />
										</xsl:element>
									</li>
								</xsl:for-each>
							</ul>
						</div>
					</div>
					<!--end header --><!-- main -->
					<div id="main">
						<div id="sidebar">
							<p>
								<strong></strong>
								<span style="font-weight: bold;">Questions?</span>
							</p>
							<p>Please email authors for questions.</p>
						</div>
						<div id="text">
							<h2>
								<strong>
									<xsl:value-of select="title" />
									<xsl:element name="a">
										<xsl:attribute name="name">
							<xsl:value-of select='@id' />
						</xsl:attribute>
									</xsl:element>
								</strong>
							</h2>
							<xsl:copy-of select="content" />
						</div>
						<hr></hr>
						<div id="system">
							<h2>
								System
								</h2>
							<p>
								<xsl:apply-templates select="/rdf:RDF/oor:System" />
							</p>
						</div>
						<hr></hr>
						<div id="actors">
							<h2>
								System Actors
								</h2>
							<p>
								<xsl:apply-templates select="/rdf:RDF/oor:Actor" />
							</p>
						</div>
						<hr></hr>
						<div id="usecases">
							<h2>
								Use Cases
								</h2>
							<p>
								<xsl:apply-templates select="/rdf:RDF/oor:UseCase" />
							</p>
							<h2>
								Alternative Flows:
								</h2>
							<p>
								<xsl:apply-templates select="/rdf:RDF/oor:Alternative" />
							</p>
						</div>
					</div>
					<!-- end main --><!-- footer -->
					<div id="footer">
						<div id="left_footer">Design: © Copyright 2008 Blacker</div>
						<div id="right_footer"><!-- DO NOT DELETE THIS LINK! READ THE LICENSE! -->
							Design by
							<a href="http://www.realitysoftware.ca" title="Website Design">Reality
								Software</a> and Maximo Gurmendez
							<!-- ****************************************** -->
						</div>
					</div>
					<!-- end footer -->
				</div>
			</body>
		</html>
	</xsl:template>


	<xsl:template match="oor:System">
		<div id="text">
			<h3>
				<strong>
					<xsl:value-of select="oor:name" />
					<xsl:element name="a">
						<xsl:attribute name="name">
							<xsl:value-of select='@rdf:ID' />
						</xsl:attribute>
					</xsl:element>
					<xsl:if test="@rdf:ID">
						<font color="purple">
							[
							<xsl:value-of select="@rdf:ID" />
							]
						</font>
					</xsl:if>
				</strong>
			</h3>
			<table
				style="text-align: left; background-color: rgb(253, 255, 223); width: 100%;"
				border="1" cellpadding="2" cellspacing="2">
				<tbody>
					<tr>
						<td>
							<span style="font-weight: bold;">Name: </span>
							<xsl:copy-of select="oor:name" />
						</td>
					</tr>

					<tr>
						<td>
							<span style="font-weight: bold;">Authors: </span>
							<xsl:for-each select="oor:author">
								<xsl:value-of select="." />
								-
							</xsl:for-each>

						</td>
					</tr>
					<tr>
						<td>
							<span style="font-weight: bold;">Description: </span>
							<xsl:copy-of select="oor:exposition" />
						</td>
					</tr>

					<tr>
						<td>
							<span style="font-weight: bold;">Organizations: </span>
							<xsl:for-each select="oor:organization">
								<xsl:value-of select="." />
								-
							</xsl:for-each>
						</td>
					</tr>
					<tr>
						<td>
							<span style="font-weight: bold;">Creation Date: </span>
							<xsl:copy-of select="oor:creationDate" />
						</td>
					</tr>

				</tbody>
			</table>
			<p></p>
		</div>
	</xsl:template>



	<xsl:template match="oor:Actor">
		<div id="text">
			<h3>
				<strong>
					<xsl:value-of select="oor:name" />
					<xsl:element name="a">
						<xsl:attribute name="name">
							<xsl:value-of select='@rdf:ID' />
						</xsl:attribute>
					</xsl:element>
					<xsl:if test="@rdf:ID">
						<font color="purple">
							[
							<xsl:value-of select="@rdf:ID" />
							]
						</font>
					</xsl:if>
				</strong>
			</h3>
			<table
				style="text-align: left; background-color: rgb(253, 255, 223); width: 100%;"
				border="1" cellpadding="2" cellspacing="2">
				<tbody>
					<tr>
						<td>
							<span style="font-weight: bold;">Description: </span>
							<xsl:copy-of select="oor:exposition" />
						</td>
					</tr>
					<xsl:for-each select="oor:kindOf">
						<tr>
							<td>
								<span style="font-weight: bold;">KindOf: </span>
								<font color="purple">
									<xsl:element name="a">
										<xsl:attribute name="href">
							<xsl:value-of select="./@rdf:resource" />
						</xsl:attribute>
										<xsl:value-of select="./@rdf:resource" />
									</xsl:element>
								</font>

							</td>
						</tr>
					</xsl:for-each>
				</tbody>
			</table>
			<p></p>
		</div>
	</xsl:template>

	<xsl:template match="oor:UseCase">
		<xsl:call-template name="flow" />
	</xsl:template>

	<xsl:template match="oor:Alternative">
		<xsl:call-template name="flow" />
	</xsl:template>

	<xsl:template name="flow">
		<div id="text">
			<h3>
				<strong>
					<xsl:value-of select="oor:name" />
					<xsl:element name="a">
						<xsl:attribute name="name">
							<xsl:value-of select='@rdf:ID' />
						</xsl:attribute>
					</xsl:element>
					<xsl:if test="@rdf:ID">
						<font color="purple">
							[
							<xsl:value-of select="@rdf:ID" />
							]
						</font>
					</xsl:if>
				</strong>
			</h3>
			<p>
				<xsl:copy-of select="content[1]" />
				<p />
				<table
					style="text-align: left; background-color: rgb(253, 255, 223); width: 100%;"
					border="1" cellpadding="2" cellspacing="2">
					<tbody>
						<xsl:if test="oor:actors">
							<tr>
								<td>
									<span style="font-weight: bold;">Actors:</span>
									<xsl:copy-of select="oor:actors" />
								</td>
							</tr>
						</xsl:if>

						<xsl:if test="oor:precondition">
							<tr>
								<td>
									<span style="font-weight: bold;">Preconditions:</span>
									<xsl:copy-of select="oor:precondition" />
								</td>
							</tr>
						</xsl:if>
						<tr>
							<td>
								<span style="font-weight: bold;">
									Description: </span>
								<br />
								<xsl:copy-of select="oor:exposition" />
							</td>
						</tr>
						<tr>
							<td>
								<span style="font-weight: bold;">
									Step-by-step
									Description:
									<br />
								</span>
								<ol>
									<xsl:call-template name="display-steps">
										<xsl:with-param name="step" select="oor:first/oor:Step" />
									</xsl:call-template>
								</ol>
							</td>
						</tr>
						<xsl:if test="authors">
							<tr>
								<td>
									<span style="font-weight: bold;">Author:</span>
									<xsl:copy-of select="author" />
								</td>
							</tr>
						</xsl:if>
						<xsl:if test="trace">
							<tr>
								<td>
									<span style="font-weight: bold;">Tracing:</span>
									<xsl:call-template name="output-tokens">
										<xsl:with-param name="list">
											<xsl:value-of select="trace" />
										</xsl:with-param>
										<xsl:with-param name="file">
											<xsl:value-of select="trace/@file" />
										</xsl:with-param>
									</xsl:call-template>
								</td>
							</tr>
						</xsl:if>
					</tbody>
				</table>



				<br />
			</p>
		</div>


	</xsl:template>

	<xsl:template name="display-steps">
		<xsl:param name="step" />
		<xsl:if test="$step/oor:specification">
			<li>
				<xsl:if test="$step/oor:stepInitiator">
					<font color="purple">
						<xsl:element name="a">
							<xsl:attribute name="href">
							<xsl:value-of select="$step/oor:stepInitiator/@rdf:resource" />
						</xsl:attribute>[<xsl:value-of select="$step/oor:stepInitiator/@rdf:resource" />]</xsl:element> -
					</font>
				</xsl:if>

				<xsl:copy-of select="$step/oor:specification/." />
			</li>
		</xsl:if>
		<xsl:for-each select="$step/oor:startAfter">
			<li>
				Alternative Flow:
				<font color="purple">
					<xsl:element name="a">
						<xsl:attribute name="href">
							<xsl:value-of select="./@rdf:resource" />
						</xsl:attribute>
						[<xsl:value-of select="./@rdf:resource" />]
					</xsl:element>
				</font>
			</li>
		</xsl:for-each>


		<xsl:for-each select="$step/oor:includes">
			<li>
				Includes:
				<font color="purple">
					<xsl:element name="a">
						<xsl:attribute name="href">
							<xsl:value-of select="./@rdf:resource" />
						</xsl:attribute>
						[<xsl:value-of select="./@rdf:resource" />]
					</xsl:element>
				</font>
			</li>
		</xsl:for-each>

		<xsl:if test="$step/oor:next">
			<xsl:call-template name="display-steps">
				<xsl:with-param name="step" select="$step/oor:next/oor:Step" />
			</xsl:call-template>
		</xsl:if>
	</xsl:template>

	<xsl:template name="output-tokens">
		<xsl:param name="list" />
		<xsl:param name="file" />
		<xsl:variable name="newlist" select="concat(normalize-space($list), ' ')" />
		<xsl:variable name="first" select="substring-before($newlist, ' ')" />
		<xsl:variable name="remaining" select="substring-after($newlist, ' ')" />
		<xsl:element name="a">
			<xsl:attribute name="href">
			<xsl:value-of select="$file" />#<xsl:value-of
				select="$first" />
		</xsl:attribute>
			[
			<xsl:value-of select="$first" />
			]
		</xsl:element>
		<xsl:if test="$remaining">
			<xsl:call-template name="output-tokens">
				<xsl:with-param name="list" select="$remaining" />
				<xsl:with-param name="file" select="$file" />
			</xsl:call-template>
		</xsl:if>
	</xsl:template>


</xsl:stylesheet>
