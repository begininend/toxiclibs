/* 
 * Copyright (c) 2006, 2007 Karsten Schmidt
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * http://creativecommons.org/licenses/LGPL/2.1/
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package toxi.geom;

import toxi.math.FastMath;
import toxi.math.InterpolateStrategy;

public class Vec3D {
	public float x, y, z;

	public Vec3D() {
		x = y = z = 0;
	}

	public Vec3D(float a, float b, float c) {
		x = a;
		y = b;
		z = c;
	}

	public Vec3D(Vec3D v) {
		set(v);
	}

	public Vec3D set(Vec3D v) {
		x = v.x;
		y = v.y;
		z = v.z;
		return this;
	}

	public Vec3D set(float a, float b, float c) {
		x = a;
		y = b;
		z = c;
		return this;
	}

	public boolean isZeroVector() {
		return x == 0 && y == 0 && z == 0;
	}

	public Vec3D getNormalized() {
		return new Vec3D(this).normalize();
	}

	public Vec3D normalize() {
		float mag = FastMath.sqrt(x * x + y * y + z * z);
		if (mag > 0) {
			mag = 1f / mag;
			x *= mag;
			y *= mag;
			z *= mag;
		}
		return this;
	}

	public float magnitude() {
		return FastMath.sqrt(x * x + y * y + z * z);
	}

	public float magSquared() {
		return x * x + y * y + z * z;
	}

	public float distanceTo(Vec3D v) {
		float dx = x - v.x;
		float dy = y - v.y;
		float dz = z - v.z;
		return FastMath.sqrt(dx * dx + dy * dy + dz * dz);
	}

	public float distanceToSquared(Vec3D v) {
		float dx = x - v.x;
		float dy = y - v.y;
		float dz = z - v.z;
		return dx * dx + dy * dy + dz * dz;
	}

	public Vec3D sub(Vec3D v) {
		return new Vec3D(x - v.x, y - v.y, z - v.z);
	}

	public Vec3D sub(float a, float b, float c) {
		return new Vec3D(x - a, y - b, z - c);
	}

	public Vec3D subSelf(Vec3D v) {
		x -= v.x;
		y -= v.y;
		z -= v.z;
		return this;
	}

	public Vec3D subSelf(float a, float b, float c) {
		x -= a;
		y -= b;
		z -= c;
		return this;
	}

	public Vec3D add(Vec3D v) {
		return new Vec3D(x + v.x, y + v.y, z + v.z);
	}

	public Vec3D add(float a, float b, float c) {
		return new Vec3D(x + a, y + b, z + c);
	}

	public Vec3D addSelf(Vec3D v) {
		x += v.x;
		y += v.y;
		z += v.z;
		return this;
	}

	public Vec3D addSelf(float a, float b, float c) {
		x += a;
		y += b;
		z += c;
		return this;
	}

	public Vec3D scale(float s) {
		return new Vec3D(x * s, y * s, z * s);
	}

	public Vec3D scale(float a, float b, float c) {
		return new Vec3D(x * a, y * b, z * c);
	}

	public Vec3D scale(Vec3D s) {
		return new Vec3D(x * s.x, y * s.y, z * s.z);
	}

	public Vec3D scaleSelf(Vec3D s) {
		x *= s.x;
		y *= s.y;
		z *= s.z;
		return this;
	}

	public Vec3D scaleSelf(float s) {
		x *= s;
		y *= s;
		z *= s;
		return this;
	}

	public Vec3D scaleSelf(float a, float b, float c) {
		x *= a;
		y *= b;
		z *= c;
		return this;
	}

	public Vec3D invert() {
		x = -x;
		y = -y;
		z = -z;
		return this;
	}

	public Vec3D getInverted() {
		return new Vec3D(-x, -y, -z);
	}

	public Vec3D cross(Vec3D v) {
		return new Vec3D(y * v.z - v.y * z, z * v.x - v.z * x, x * v.y - v.x
				* y);
	}

	public Vec3D crossSelf(Vec3D v) {
		x = y * v.z - v.y * z;
		y = z * v.x - v.z * x;
		z = x * v.y - v.x * y;
		return this;
	}

	public Vec3D crossInto(Vec3D v, Vec3D result) {
		float rx = y * v.z - v.y * z;
		float ry = z * v.x - v.z * x;
		float rz = x * v.y - v.x * y;
		result.set(rx, ry, rz);
		return result;
	}

	public float dot(Vec3D v) {
		return x * v.x + y * v.y + z * v.z;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer(32);
		sb.append("{x:");
		sb.append(x);
		sb.append(", y:");
		sb.append(y);
		sb.append(", z:");
		sb.append(z);
		sb.append("}");
		return sb.toString();
	}

	public Vec3D interpolateTo(Vec3D v, float f) {
		return new Vec3D(x + (v.x - x) * f, y + (v.y - y) * f, z + (v.z - z)
				* f);
	}

	public Vec3D interpolateTo(Vec3D v, float f, InterpolateStrategy s) {
		return new Vec3D(s.interpolate(x, v.x, f), s.interpolate(y, v.y, f), s
				.interpolate(z, v.z, f));
	}

	public Vec3D interpolateToSelf(Vec3D v, float f) {
		x += (v.x - x) * f;
		y += (v.y - y) * f;
		z += (v.z - z) * f;
		return this;
	}

	public Vec3D interpolateToSelf(Vec3D v, float f, InterpolateStrategy s) {
		x = s.interpolate(x, v.x, f);
		y = s.interpolate(y, v.y, f);
		z = s.interpolate(z, v.z, f);
    return this;
	}

	public float angleBetween(Vec3D v) {
		return (float) Math.acos(FastMath.min(dot(v), 1.0f));
	}

	public float angleBetween(Vec3D v, boolean forceNormalize) {
		float theta;
		if (forceNormalize) {
			theta = FastMath.min(getNormalized().dot(v.getNormalized()), 1.0f);
		} else {
			theta = FastMath.min(dot(v), 1.0f);
		}
		return (float) Math.acos(theta);
	}

	// intersection code adapted from C version at http://www.peroxide.dk/

	/**
	 * Normalized directional vectors expected
	 * 
	 * @return: distance to plane in world units, -1 if no intersection.
	 */

	public float intersectRayPlane(Vec3D rayDir, Vec3D pOrigin, Vec3D pNormal) {
		float d = -pNormal.dot(pOrigin);
		float numer = pNormal.dot(this) + d;
		float denom = pNormal.dot(rayDir);

		// normal is orthogonal to vector, cant intersect
		if (FastMath.abs(denom) < FastMath.EPS)
			return -1;

		return -(numer / denom);
	}

	/**
	 * Normalized directional vectors expected
	 * 
	 * @return distance to sphere in world units, -1 if no intersection.
	 */

	public float intersectRaySphere(Vec3D rayDir, Vec3D sOrigin, float sRadius) {
		Vec3D q = sOrigin.sub(this);
		float c = q.magnitude();
		float v = q.dot(rayDir);
		float d = sRadius * sRadius - (c * c - v * v);

		// If there was no intersection, return -1
		if (d < 0.0)
			return -1;

		// Return the distance to the [first] intersecting point
		return v - FastMath.sqrt(d);
	}

	/**
	 * Notes : Triangle should be defined in clockwise order a,b,c
	 * 
	 * @return: TRUE if point is in triangle, FALSE if not.
	 */

	public boolean isInTriangle(Vec3D a, Vec3D b, Vec3D c) {
		Vec3D v1 = sub(a).normalize();
		Vec3D v2 = sub(b).normalize();
		Vec3D v3 = sub(c).normalize();

		float total_angles = (float) Math.acos(v1.dot(v2));
		total_angles += (float) Math.acos(v2.dot(v3));
		total_angles += (float) Math.acos(v3.dot(v1));

		return (FastMath.abs(total_angles - FastMath.TWO_PI) <= 0.005f);
	}

	/**
	 * Helper function for closestPointOnTriangle()
	 * 
	 * @return closest point on line segment
	 */

	public Vec3D closestPointOnLine(Vec3D a, Vec3D b) {
		// Determine t (the length of the vector from �a� to 'this')
		Vec3D c = sub(a);
		Vec3D v = b.sub(a);

		float d = v.magnitude();
		v.normalize();

		float t = v.dot(c);

		// Check to see if �t� is beyond the extents of the line segment
		if (t < 0.0f)
			return a;
		if (t > d)
			return b;

		// Return the point between 'a' and 'b'
		// set length of V to t. V is normalized so this is easy
		v.scale(t);

		return a.add(v);
	}

	/**
	 * @return closest point on line triangle edge
	 */

	public Vec3D closestPointOnTriangle(Vec3D a, Vec3D b, Vec3D c) {
		Vec3D Rab = closestPointOnLine(a, b);
		Vec3D Rbc = closestPointOnLine(b, c);
		Vec3D Rca = closestPointOnLine(c, a);

		float dAB = sub(Rab).magnitude();
		float dBC = sub(Rbc).magnitude();
		float dCA = sub(Rca).magnitude();

		float min = dAB;
		Vec3D result = Rab;

		if (dBC < min) {
			min = dBC;
			result = Rbc;
		}
		if (dCA < min)
			result = Rca;

		return result;
	}

	/**
	 * @return TRUE if point is in sphere, FALSE if not.
	 */

	public boolean isInSphere(Vec3D sO, float sR) {
		float d = this.sub(sO).magnitude();
		return (d <= sR);
	}

	/**
	 * @return a unit normal vector to the tangent plane of the ellipsoid in the
	 *         point.
	 */

	public Vec3D tangentPlaneNormalOfEllipsoid(Vec3D eO, Vec3D eR) {
		Vec3D p = this.sub(eO);

		float a2 = eR.x * eR.x;
		float b2 = eR.y * eR.y;
		float c2 = eR.z * eR.z;

		return new Vec3D(p.x / a2, p.y / b2, p.z / c2).normalize();
	}

	/**
	 * @return One of 3 classification codes
	 */

	public static final int PLANE_FRONT = -1;

	public static final int PLANE_BACK = 1;

	public static final int ON_PLANE = 0;

	public int classifyPoint(Vec3D pO, Vec3D pN) {
		Vec3D dir = pO.sub(this);
		float d = dir.dot(pN);
		if (d < -FastMath.EPS)
			return PLANE_FRONT;
		else if (d > FastMath.EPS)
			return PLANE_BACK;

		return ON_PLANE;
	}

	// from Real-Time Collision Detection by Christer Ericson, published
	// by Morgan Kaufmann Publishers, Copyright 2005 Elsevier Inc

	public Vec3D closestPointTriangle(Vec3D p, Vec3D a, Vec3D b, Vec3D c) {
		Vec3D ab = b.sub(a);
		Vec3D ac = c.sub(a);
		Vec3D bc = c.sub(b);

		Vec3D pa = p.sub(a);
		Vec3D pb = p.sub(b);
		Vec3D pc = p.sub(c);

		// Compute parametric position s for projection P' of P on AB,
		// P' = A + s*AB, s = snom/(snom+sdenom)
		float snom = pa.dot(ab);
		float sdenom = pb.dot(a.sub(b));

		// Compute parametric position t for projection P' of P on AC,
		// P' = A + t*AC, s = tnom/(tnom+tdenom)
		float tnom = pa.dot(ac);
		float tdenom = pc.dot(a.sub(c));

		if (snom <= 0.0f && tnom <= 0.0f)
			return a; // Vertex region early out

		// Compute parametric position u for projection P' of P on BC,
		// P' = B + u*BC, u = unom/(unom+udenom)
		float unom = pb.dot(bc);
		float udenom = pc.dot(b.sub(c));

		if (sdenom <= 0.0f && unom <= 0.0f)
			return b; // Vertex region early out
		if (tdenom <= 0.0f && udenom <= 0.0f)
			return c; // Vertex region early out

		// P is outside (or on) AB if the triple scalar product [N PA PB] <= 0
		Vec3D n = ab.cross(ac);
		float vc = n.dot(a.sub(p).crossSelf(b.sub(p)));

		// If P outside AB and within feature region of AB,
		// return projection of P onto AB
		if (vc <= 0.0f && snom >= 0.0f && sdenom >= 0.0f) {
			// return a + snom / (snom + sdenom) * ab;
			return a.add(ab.scaleSelf(snom / (snom + sdenom)));
		}

		// P is outside (or on) BC if the triple scalar product [N PB PC] <= 0
		float va = n.dot(b.sub(p).crossSelf(c.sub(p)));
		// If P outside BC and within feature region of BC,
		// return projection of P onto BC
		if (va <= 0.0f && unom >= 0.0f && udenom >= 0.0f) {
			// return b + unom / (unom + udenom) * bc;
			return b.add(bc.scaleSelf(unom / (unom + udenom)));
		}

		// P is outside (or on) CA if the triple scalar product [N PC PA] <= 0
		float vb = n.dot(c.sub(p).crossSelf(a.sub(p)));
		// If P outside CA and within feature region of CA,
		// return projection of P onto CA
		if (vb <= 0.0f && tnom >= 0.0f && tdenom >= 0.0f) {
			// return a + tnom / (tnom + tdenom) * ac;
			return a.add(ac.scaleSelf(tnom / (tnom + tdenom)));
		}

		// P must project inside face region. Compute Q using barycentric
		// coordinates
		float u = va / (va + vb + vc);
		float v = vb / (va + vb + vc);
		float w = 1.0f - u - v; // = vc / (va + vb + vc)
		// return u * a + v * b + w * c;
		return a.scale(u).addSelf(b.scale(v)).addSelf(c.scale(w));
	}

	// Returns true if sphere s intersects triangle ABC, false otherwise.
	// The Vec3D p on abc closest to the sphere center is also returned
	public boolean intersectSphereTriangle(float r, Vec3D a, Vec3D b, Vec3D c,
			Vec3D result) {
		// Find Vec3D P on triangle ABC closest to sphere center
		result.set(closestPointTriangle(this, a, b, c));

		// Sphere and triangle intersect if the (squared) distance from sphere
		// center to Vec3D p is less than the (squared) sphere radius
		Vec3D v = result.sub(this);
		return v.x * v.x + v.y * v.y + v.z * v.z <= r * r;
	}

	public static final Vec3D randomVector() {
		Vec3D rnd = new Vec3D((float) Math.random() * 2 - 1, (float) Math
				.random() * 2 - 1, (float) Math.random() * 2 - 1);
		return rnd.normalize();
	}
}