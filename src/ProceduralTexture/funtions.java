package ProceduralTexture;


/*Farbverlauf
 * public RGBColor getColor(RGBColor g, Vector3f v){
 *				Vector3f w = new Vector3f(v);
 *				w.normalize();
 *				g.add(new RGBColor(w));
 *				return new RGBColor(g).sanatize();
 *			}
*/

/* Schwarz/Weisse Kreise
 * public RGBColor getColor(RGBColor g, Vector3f v){
 * return Math.floor(v.length())%2==0?new RGBColor(0f,0f,0f):new RGBColor(0.5f,.5f,.5f);
}*/

/*Swarz/Weisse Streifen
 * public RGBColor getColor(RGBColor g, Vector3f v){
 *				return Math.floor(v.x)%2==0?new RGBColor(0f,0f,0f):new RGBColor(0.5f,.5f,.5f);
 *				}
 */
/*Schachbrett
 * public RGBColor getColor(RGBColor g, Vector3f v){
 * RGBColor w=new RGBColor(0.5f,.5f,.5f);
 * //w.mult(1-(v.length()/50));
 * w.sanatize();
 * return (Math.floor(v.x*3.5)%2==0)^(Math.floor(v.z*3.5)%2==0)^(Math.floor(v.y*3.5)%2==0)?new RGBColor(0f,0f,0f):w;
 * }
 */