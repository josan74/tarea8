<?xml version='1.0' encoding='iso‐8859‐1'?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				<h1>Mi Biblioteca</h1>
				<table>
					<tr bgcolor="#889988">
						<th>Título</th>
						<th>Autor</th>
						<th>Precio</th>
					</tr>
					<xsl:for‐each select="librería/libro">
						<xsl:choose>
							<xsl:when test="precio &lt; 12">
								<tr>
									<td bgcolor="#ff0000">
										<xsl:value ‐ of select="titulo" />
									</td>
									<td bgcolor="#ff0000">
										<xsl:value ‐ of select="autor" />
									</td>
									<td bgcolor="#ff0000">
										<xsl:value ‐ of select="precio" />
									</td>
								</tr>
							</xsl:when>
							<xsl:when test="precio &gt; 21">
								<tr>
									<td bgcolor="#00ff00">
										<xsl:value‐of select="titulo" />
									</td>
									<td bgcolor="#00ff00">
										<xsl:value‐of select="autor" />
									</td>
									<td bgcolor="#00ff00">
										<xsl:value‐of select="precio" />
									</td>
								</tr>
							</xsl:when>
							<xsl:otherwise>
								<tr>
									<td bgcolor="#0000ff">
										<xsl:value‐of select="titulo" />
									</td>
									<td bgcolor="#0000ff">
										<xsl:value‐of select="autor" />
									</td>
									<td bgcolor="#0000ff">
										<xsl:value‐of select="precio" />
									</td>
								</tr>
							</xsl:otherwise>
						</xsl:choose>
					</xsl:for‐each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>

