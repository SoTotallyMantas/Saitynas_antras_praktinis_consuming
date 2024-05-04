<?xml version = "1.0" encoding = "UTF-8"?>
<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:fo="http://www.w3.org/1999/XSL/Format"
        version="1.0"
        xmlns:gen="http://lt.viko.eif/mantas/springsoap/gen"
        exclude-result-prefixes="#all">

    <xsl:output encoding="UTF-8" indent="yes" method="xml" standalone="no" omit-xml-declaration="no"/>

    <!-- Base template for PDF layout -->
    <xsl:template match="/">
        <fo:root language="EN">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="A4-portrail" page-height="297mm"
                                       page-width="210mm" margin-top="5mm"
                                       margin-bottom="5mm" margin-left="5mm"
                                       margin-right="5mm">
                    <fo:region-body margin-top="25mm" margin-bottom="20mm"/>
                    <fo:region-before region-name="xsl-region-before"
                                      extent="25mm" display-align="before"
                                      precedence="true"/>

                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="A4-portrail">
                <fo:flow flow-name="xsl-region-body"
                         border-collapse="collapse" reference-orientation="0">

                    <!-- Apply specific templates based on response type -->
                    <xsl:apply-templates select="/gen:getClientOrdersResponse"/>
                    <xsl:apply-templates select="/gen:getDispatchNumberOrdersResponse"/>
                    <xsl:apply-templates select="/gen:getDispatchOrdersResponse"/>
                    <xsl:apply-templates select="/gen:getDriverOrdersResponse"/>
                    <xsl:apply-templates select="/gen:getDriverPlateOrdersResponse"/>
                    <!-- Add more specific templates for other response types if needed -->
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

    <!-- Template for processing getResponse -->
    <xsl:template match="gen:getClientOrdersResponse">
        <xsl:apply-templates select="gen:OrderList"/>
    </xsl:template>
    <xsl:template match="gen:getDispatchNumberOrdersResponse">
        <xsl:apply-templates select="gen:OrderList"/>
    </xsl:template>
    <xsl:template match="gen:getDispatchOrdersResponse">
        <xsl:apply-templates select="gen:OrderList"/>
    </xsl:template>
    <xsl:template match="gen:getDriverOrdersResponse">
        <xsl:apply-templates select="gen:OrderList"/>
    </xsl:template>
    <xsl:template match="gen:getDriverPlateOrdersResponse">
        <xsl:apply-templates select="gen:OrderList"/>
    </xsl:template>

    <!-- Template for processing orders list -->
    <xsl:template match="gen:OrderList">
        <fo:block> <!-- Adjust this block as needed -->
            <fo:block text-align="center" display-align="center">
                <fo:external-graphic src="taxipic.svg" content-width="scale-to-fit" width="25%" />
            </fo:block>
            <fo:block font-weight="bold" text-align="center" color="blue">Order</fo:block>
            <xsl:apply-templates select="gen:Order"/>
        </fo:block>
    </xsl:template>

    <!-- Template for processing orders -->
    <xsl:template match="gen:Order">
        <!-- Order block -->
        <fo:block font-size="14pt" font-family="Arial, sans-serif" space-after="5mm">

            <!-- Order ID -->
            <fo:block font-weight="bold" text-align="center">
            <xsl:text>Order ID: </xsl:text><xsl:value-of select="@id"/>
        </fo:block>
            <!-- Order Address -->
            <fo:block font-weight="bold" text-align="center">
            <xsl:text>Address: </xsl:text><xsl:value-of select="gen:address"/>
        </fo:block>
        </fo:block>
        <!-- Client -->
        <fo:block font-weight="bold" margin-top="10pt" font-size="12pt">
        <fo:block font-weight="bold" text-align="center" color="green">Client Details</fo:block>
        </fo:block>
        <!-- Client First Last name -->
        <fo:block font-weight="bold" text-align="center">
            <xsl:text>Client Name: </xsl:text><xsl:value-of select="gen:client/gen:FirstName"/>  <xsl:text>  </xsl:text> <xsl:value-of select="gen:client/gen:Lastname"/>
        </fo:block>
        <!-- Client Phone Number -->
        <fo:block font-weight="bold" text-align="center">
            <xsl:text>Client Phone Number: </xsl:text><xsl:value-of select="gen:client/gen:Phone_Number"/>
        </fo:block>
        <!-- Driver  -->
        <fo:block font-weight="bold" margin-top="10pt" font-size="12pt">
        <fo:block font-weight="bold" text-align="center" color="green">Driver Details</fo:block>
        </fo:block>
        <!-- Driver First Last name -->
        <fo:block font-weight="bold" text-align="center">
            <xsl:text>Driver Name: </xsl:text><xsl:value-of select="gen:driver/gen:FirstName"/> <xsl:text>  </xsl:text> <xsl:value-of select="gen:driver/gen:Lastname"/>
        </fo:block>
        <!-- Driver Phone Number -->
        <fo:block font-weight="bold" text-align="center">
            <xsl:text>Driver Phone Number: </xsl:text><xsl:value-of select="gen:driver/gen:Phone_Number"/>
        </fo:block>
        <!-- Driver License Plate -->
        <fo:block font-weight="bold" text-align="center">
            <xsl:text>Driver License Plate: </xsl:text><xsl:value-of select="gen:driver/gen:License_Plate"/>
        </fo:block>

        <fo:block font-weight="bold" margin-top="10pt" font-size="12pt">
            <fo:block font-weight="bold" text-align="center" color="green">Dispatch Details</fo:block>
        </fo:block>
            <!-- Dispatch First Last name -->
        <fo:block font-weight="bold" text-align="center">
            <xsl:text>Dispatch Name: </xsl:text><xsl:value-of select="gen:dispatch/gen:FirstName"/> <xsl:text>  </xsl:text> <xsl:value-of select="gen:dispatch/gen:Lastname"/>
        </fo:block>
        <!-- Dispatch Phone Number -->
        <fo:block font-weight="bold" text-align="center">
            <xsl:text>Dispatch Phone Number: </xsl:text><xsl:value-of select="gen:dispatch/gen:Phone_Number"/>
        </fo:block>
        <!-- Dispatch Work Number -->
        <fo:block font-weight="bold" text-align="center">
            <xsl:text>Dispatch Work Number: </xsl:text><xsl:value-of select="gen:dispatch/gen:Work_Number"/>
        </fo:block>
        <fo:block font-weight="bold" text-align="center">
            <xsl:text>------------------------------------------</xsl:text>
        </fo:block>
    </xsl:template>

    <!-- Add more templates for other response types as needed -->

</xsl:stylesheet>